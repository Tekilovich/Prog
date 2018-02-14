import dao.CustomerDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import model.Customer;
import model.Order;
import model.Product;
import model.ProductList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Shop {

    private CustomerDAO customerDAO;
    private ProductDAO productDAO;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private Connection connection;
    private Customer customer;
    private OrderDAO orderDAO;

    public Shop() {
        connection = ConnectionFactory.getConnection();
        customerDAO = new CustomerDAO(connection);
        productDAO = new ProductDAO(connection);
        orderDAO = new OrderDAO(connection);
    }

    public void login() {

        String login;
        String password;

        try {
            System.out.println("login:");
            login = reader.readLine();
            System.out.println("password:");
            password = reader.readLine();

            if ((customer = customerDAO.getCustomer(login, password)) != null) {
                enter();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void enter() {

        try {
            try {
                while (true) {
                    System.out.println("1: add new product");
                    System.out.println("2: make order");
                    System.out.println("0: exit");
                    System.out.println("->");

                    String value = reader.readLine();
                    switch (value) {
                        case "1":
                            addProduct();
                            break;
                        case "2":
                            makeOrder();
                            break;
                        case "0":
                            return;
                        default:
                            return;
                    }
                }


            } finally {
                reader.close();
                if (connection != null) connection.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    private void addProduct()  throws SQLException, IOException{

        String name;
        double price;

        while (true) {
            System.out.println("Input product name:");
            name = reader.readLine();
            while (true) {
                try {
                    System.out.println("Input price");
                    price = Double.parseDouble(reader.readLine());
                    break;
                } catch (Exception ex) {
                    System.out.println("Format error");
                }
            }

            productDAO.createNewProduct(name, price);

            System.out.println("Do you want input next product? y or n");
            String s = reader.readLine();
            if (!s.equals("y")) break;
        }

    }

    private void makeOrder()  throws SQLException{

        Order order;
        List<Product> productsForOrder = new ArrayList<Product>();

        String name;
        double price;

        while (true) {
            System.out.println("Input product name:");
            try {
                name = reader.readLine();
                boolean exit = false;
                while (!exit) {
                    try {
                        ProductList productList = new ProductList(productDAO.findProduct(name));

                        if (!productList.isEmpty()) {
                            productList.show();
                            while (true) {
                                System.out.println("Enter the code of the item you want add to the basket " +
                                        "or press key 'c' for return");
                                try {
                                    String codeStr = reader.readLine();
                                    if (codeStr.toLowerCase().equals("c")) {
                                        exit = true;
                                        break;
                                    }
                                    int code = Integer.parseInt(codeStr);
                                    Product product = productList.getByCode(code);
                                    if (product == null) {
                                        System.out.println("Code not found!");
                                    } else {
                                        productsForOrder.add(product);
                                        System.out.println("Done!");
                                    }
                                } catch (IOException ex) {
                                    System.out.println("Format error!");
                                }
                            }
                        } else {
                            System.out.println("Not found!");
                            break;
                        }
                    } catch (Exception ex) {
                        System.out.println("Format error");
                    }
                }

                System.out.println("Do you want found another product? y or n");
                String s = reader.readLine();
                if (!s.equals("y")) break;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        System.out.println("1: Save order");
        System.out.println("2: Cancel");

        String value;
        try {
            value = reader.readLine();

            switch (value) {
                case "1":
                    order = new Order(customer, productsForOrder, LocalDateTime.now());
                    orderDAO.add(order);
                    break;
                 default:
                     break;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
