package dao;

import exeption.NoConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnector {
    private static final String url = "jdbc:mysql://localhost:3306/madb?useSSL=false&serverTimezone=UTC";
    private static final String name = "root";
    private static final String pass = "root";

    public static Connection connect() {


        try {
           // Class.forName("something.jdbc.driver.YourFubarDriver");
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, name, pass);
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (Connection) new NoConnection();
    }

//    public static void main(String[] args) throws SQLException {
//        DbConnector dbConnector = new DbConnector();
//        Connection connection = dbConnector.connect();
//        Statement statement = connection.createStatement(); //это запрос к бд используя этот коннекшн
//        String sql = "CREATE TABLE `madb`.`users` (\n" +
//                "  `name` VARCHAR(255) NULL,\n" +
//                "  `pasword` VARCHAR(255) NULL);";
//        statement.execute(sql);
//
//    }
}
