package dao;

import model.Customer;

import java.sql.*;

public class CustomerDAO extends AbstractDAO<Customer>{

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS customer";
    private static final String CREATE_TABLE = "CREATE TABLE customer (" +
            "customer_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
            "name VARCHAR(128) NOT NULL UNIQUE," +
            "password VARCHAR(128) NOT NULL" +
            ")";
    private static final String TABLE_NAME = "customer";

    Connection connection;

    public CustomerDAO(Connection connection) {
        super(connection, TABLE_NAME);
        this.connection = connection;
    }

    public void createTable() {
        try (Statement statement = connection.createStatement()){
            statement.execute(CREATE_TABLE);
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

    public Customer getCustomer(String login, String password) {

        Customer customer = null;
        int id;

        try {
            PreparedStatement psSelect = connection.prepareStatement(
                    "SELECT * FROM customer WHERE name = ?");
            psSelect.setString(1, login);
            try {
                ResultSet rs = psSelect.executeQuery();

                if (rs.next()) {
                    if (password.equals(rs.getString("password"))) {
                        id = rs.getInt("customer_id");
                    } else {
                        System.out.println("Password error!");
                        return null;
                    }
                } else {
                    createNewCustomer(login, password);
                    rs = psSelect.executeQuery();
                    rs.next();
                    id = rs.getInt("customer_id");
                }
            } finally {
                psSelect.close();
            }
            customer = new Customer(id, login);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return customer;
    }

    private void createNewCustomer(String login, String password) throws SQLException {
            PreparedStatement psInsert = connection.prepareStatement(
                    "INSERT INTO customer (name, password) VALUE (?, ?)");
            psInsert.setString(1, login);
            psInsert.setString(2, password);
            try {
                psInsert.execute();
            } finally {
                psInsert.close();
            }
    }
}
