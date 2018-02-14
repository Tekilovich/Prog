import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectingFactory {

    static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/mydb";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "***";

    public static Connection getConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        } catch (SQLException ex) {
            System.out.println("Connection error!");
        }

        return connection;
    }
}
