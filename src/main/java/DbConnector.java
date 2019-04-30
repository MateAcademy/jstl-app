import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
    private String dbUrl = "jdbc:mysql://localhost:3306/madb?useSSL=false&serverTimezone=UTC";
    private String name = "root";
    private String pass = "root";

    public Connection connect() throws SQLException {
       Connection connection = DriverManager.getConnection(dbUrl, name, pass);
       return connection;
    }

    public static void main(String[] args) throws SQLException {
        DbConnector dbConnector = new DbConnector();
        Connection connection = dbConnector.connect();

    }
}
