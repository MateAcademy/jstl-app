package dao;

import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

    public boolean getUser(User newUser) {
        Connection connnection = DbConnector.connect();
        try {
            Statement statement = connnection.createStatement();
            String sql = "SELECT * FROM madb.users WHERE name='" + newUser.getName() + "' and password = '" + newUser.getPassword() + "';";
            boolean rez = false;
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                if (resultSet.getString("name").equals(newUser.getName()) & resultSet.getString("password").equals(newUser.getPassword())) {
                    rez = true;
                }

            }
            System.out.println(sql);
            System.out.println("Есть ли такой User в базе данных: " + rez);
            return rez;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<User> getUsers() {
        List<User> list = new ArrayList<>();
        Connection connnection = DbConnector.connect();
        try {
            Statement statement = connnection.createStatement();
            String sql = "SELECT * FROM madb.users";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                User user = new User(name, password);
                list.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    public void delUser(User user) {
        Connection connnection = DbConnector.connect();
        try {
            String query = "DELETE FROM madb.users WHERE name='" + user.getName()+"' and password = '"+user.getPassword()+"';";
            Statement statement = connnection.createStatement();
            statement.executeUpdate(query);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editUser(String password, String name) {
        Connection connnection = DbConnector.connect();
        try {
            String query = "UPDATE users SET password = '" + password + "' WHERE name='" + name + "';";
            Statement statement = connnection.createStatement();
            statement.executeUpdate(query);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}


