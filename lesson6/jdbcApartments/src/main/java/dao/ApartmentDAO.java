package dao;

import model.Apartment;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApartmentDAO extends AbstractDAO<Apartment> {

    private static final String DROP_TAVLE = "DROP TABLE IF EXISTS apartments";
    private static final String CREATE_TABLE = "CREATE TABLE apartments (\n" +
            "    -> id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,\n" +
            "    -> area VARCHAR(128) NOT NULL,\n" +
            "    -> address VARCHAR(128) NOT NULL,\n" +
            "    -> sqft DOUBLE NOT NULL,\n" +
            "    -> rooms INT NOT NULL,\n" +
            "    -> price DOUBLE NOT NULL\n" +
            "    -> )";
    private static final String TABLE_NAME = "apartment";

    Connection connection;

    public ApartmentDAO(Connection connection) {
        super(connection, TABLE_NAME);
        this.connection = connection;
    }

    @Override
    public Connection getConnection() {
        return super.getConnection();
    }

    @Override
    public void init() {
       try (Statement st = connection.createStatement()){



       } catch (SQLException ex) {
           ex.printStackTrace();
       }
    }

    public void addApartment(Apartment apartment)  throws SQLException, IOException{

        PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO apartments (area, address, sqft, rooms, price) VALUES (?, ?, ?, ?, ?)");
        connection.setAutoCommit(false);

            ps.setString(1, apartment.getArea());
            ps.setString(2, apartment.getAddress());
            ps.setDouble(3, apartment.getSqft());
            ps.setInt(4, apartment.getRooms());
            ps.setDouble(5, apartment.getPrice());
            ps.executeUpdate();

            connection.commit();

    }

    public List<Apartment> findByParameters(String paramType, String parameter) throws IOException, SQLException {
        List<Apartment> apartments = new ArrayList<Apartment>();

        String quary = String.format("SELECT * FROM apartments WHERE %s LIKE ?", paramType);

        PreparedStatement ps;
        ps = connection.prepareStatement(quary);
        ps.setString(1, '%' + parameter + '%');

        try {
            ResultSet resultSet = ps.executeQuery();

            try {

                while (resultSet.next()) {
                    apartments.add(new Apartment(
                            resultSet.getString("area"),
                            resultSet.getString("address"),
                            resultSet.getDouble("sqft"),
                            resultSet.getInt("rooms"),
                            resultSet.getDouble("price")
                    ));
                }
            } finally {
                resultSet.close();
            }
        } finally {
            ps.close();
        }

        return apartments;
    }
}
