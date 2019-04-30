package dao;

import model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDao {

    public void addUser(User user) {
        Connection connnection = DbConnector.connect();
        try {
            Statement statement = connnection.createStatement();
            String sql = "INSERT INTO madb.users(name, password) VALUES ('" + user.getName() + "','" + user.getPassword() + "');";
            System.out.println(sql);
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
