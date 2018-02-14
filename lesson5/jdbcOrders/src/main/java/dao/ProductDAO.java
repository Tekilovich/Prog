package dao;

import model.Product;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO extends AbstractDAO<Product>{

    private static final String DROP_TABLE = "DROP TABLE IF EXIST PRODUCT";
    private static final String CREATE_TABLE = "CREATE TABLE product (" +
            "product_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
            "name VARCHAR(128) NOT NULL," +
            "price DOUBLE NOT NULL" +
            ")";
    private static final String TABLE_NAME = "product";

    Connection connection;

    public ProductDAO(Connection connection) {
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

    public void createNewProduct(String name, Double price) throws SQLException {
        PreparedStatement psInsert = connection.prepareStatement(
                "INSERT INTO product (name, price) VALUE (?, ?)");
        psInsert.setString(1, name);
        psInsert.setDouble(2, price);
        try {
            psInsert.execute();
        } finally {
            psInsert.close();
        }
    }

    public List<Product> findProduct(String name) throws IOException, SQLException {
        List<Product> products = new ArrayList<Product>();

        String query = "SELECT * FROM product WHERE name LIKE ?";

        PreparedStatement ps;
        ps = connection.prepareStatement(query);
        ps.setString(1, '%' + name + '%');

        try {
            ResultSet resultSet = ps.executeQuery();

            try {

                while (resultSet.next()) {
                    products.add(new Product(
                            resultSet.getInt("product_id"),
                            resultSet.getString("name"),
                            resultSet.getDouble("price")
                    ));
                }
            } finally {
                resultSet.close();
            }
        } finally {
            ps.close();
        }

        return products;
    }
}
