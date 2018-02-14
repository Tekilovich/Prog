package dao;

import model.Order;
import model.Product;

import java.sql.*;
import java.util.List;

public class OrderDAO extends AbstractDAO<Order>{

    private static final String DROP_TABLE = "DROP TABLE IF EXIST orders";
    private static final String CREATE_TABLE_orders = "CREATE TABLE orders (" +
            "orders_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
            "customer_id INT NOT NULL," +
            "date DATETIME NOT NULL" +
            ")";
    private static final String CREATE_TABLE_orders_product = "CREATE TABLE orders_product (" +
            "orders_product_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
            "orders_id INT NOT NULL," +
            "product_id INT NOT NULL" +
            ")";
    private static final String TABLE_NAME = "orders";

    Connection connection;


    public OrderDAO(Connection connection) {
        super(connection, TABLE_NAME);
        this.connection = connection;
    }

    public void createTable() {
        try (Statement statement = connection.createStatement()){
            statement.execute(CREATE_TABLE_orders);
            statement.execute(CREATE_TABLE_orders_product);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() {
        return super.getConnection();
    }

    @Override
    public void init() {
        super.init();
    }

    public void add(Order order) throws SQLException{

        PreparedStatement psInsertInOrder;
        Statement st;
        PreparedStatement psInsertInOrderProduct;

        try {
            connection.setAutoCommit(false);

            psInsertInOrder = connection.prepareStatement(
                    "INSERT INTO orders (customer_id, date) VALUE (?, ?)");
            psInsertInOrder.setInt(1, order.getCustomer().getid());
            psInsertInOrder.setTimestamp(2, Timestamp.valueOf(order.getDate()));
            psInsertInOrder.executeUpdate();

            st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT LAST_INSERT_ID()");

            if (rs.next()) {
                order.setId(rs.getInt(1));
            }

            psInsertInOrderProduct = connection.prepareStatement(
                    "INSERT INTO orders_product (orders_id, product_id) VALUE (?, ?)");

            List<Product> products = order.getProducts();

            try {
                for (Product product : products) {
                    psInsertInOrderProduct.setInt(1, order.getId());
                    psInsertInOrderProduct.setInt(2, product.getId());

                    psInsertInOrderProduct.executeUpdate();

                }
            } finally {
                psInsertInOrder.close();
            }

            connection.commit();
            System.out.println("Saved");
        } catch (Exception ex) {
            connection.rollback();
            ex.printStackTrace();
        }
    }
}
