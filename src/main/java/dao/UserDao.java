package dao;

import model.User;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.Optional;
import org.apache.log4j.Logger;

public class UserDao {
    Connection connection = DbConnector.connect();
    private static final Logger logger = Logger.getLogger(UserDao.class);

    public int addUser(User user) {
        try {
            Statement statement = connection.createStatement();
            String name = user.getName();
            String password = user.getPassword();
            String role = user.getRole();
            String sql = "INSERT INTO madb.user(name, password, role) VALUES ('" + name + "','" + password + "','"+ role +"');";
            logger.debug(sql);
            int userAddedOrNo = statement.executeUpdate(sql);
            return userAddedOrNo;
        } catch (SQLException e) {
            logger.error("Can't add user by name", e);
            return 0;
        }
    }


    public Optional<User> getUserByName(String name) {
        try {
            final String sql = "SELECT * FROM madb2.users WHERE name = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            logger.debug(sql);
            if (resultSet.next()) {
                Long userId = resultSet.getLong(1);
                String nameUser = resultSet.getString(2);
                String password = resultSet.getString(3);
                String role = resultSet.getString(4);
                User user = new User(userId, nameUser, password, role);
                return Optional.of(user);
            }
        } catch (SQLException e) {
            logger.error("Can't get user by name ", e);
        }
        return Optional.empty();
    }

    public Optional<User> getUserById(Long id) {
        try {
            String sql = "SELECT * FROM madb2.user WHERE id =?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Long userId = resultSet.getLong(1);
                String nameUser = resultSet.getString(2);
                String password = resultSet.getString(3);
                String role = resultSet.getString(4);
                User user = new User(userId, nameUser, password, role);
                return Optional.of(user);
            }
        } catch (SQLException e) {
            logger.error("Can't get user by Id ", e);
        }
        return Optional.empty();
    }

//    public void addUser(User user) {
//        try {
//            Statement statement = connection.createStatement();
//            String sql = "INSERT INTO madb.users(name, password) VALUES ('" + user.getLogin() + "','" + user.getPassword() + "');";
//            logger.debug(sql);
//            statement.execute(sql);
//        } catch (SQLException e) {
//            logger.error("Can't add user by name", e);
//        }
//    }

//    public void addUser(User user) {
//        try {
//            Statement statement = connection.createStatement();
//            String sql = "INSERT INTO madb2.users(login, password) VALUES ('" + user.getLogin() + "','" + user.getPassword() + "');";
//            logger.debug(sql);
//            statement.execute(sql);
//        } catch (SQLException e) {
//            logger.error("Can't add user by name", e);
//        }
//    }

//    public boolean getUser(User newUser) {
//        try {
//            Statement statement = connection.createStatement();
//            String sql = "SELECT * FROM madb2.users WHERE name='" + newUser.getLogin() + "' and password = '" + newUser.getPassword() + "';";
//            boolean userInDatabase = false;
//            ResultSet resultSet = statement.executeQuery(sql);
//
//            while (resultSet.next()) {
//                if (resultSet.getString("name").equals(newUser.getLogin()) & resultSet.getString("password").equals(newUser.getPassword())) {
//                    userInDatabase = true;
//                }
//
//            }
//            logger.debug(sql);
//            System.out.println("Есть ли такой User в базе данных: " + userInDatabase);
//            return userInDatabase;
//        } catch (SQLException e) {
//            logger.error("Can't get user by name " + newUser.getLogin(), e);
//        }
//        return false;
//    }

//    public boolean getUser(User newUser) {
//        try {
//            Statement statement = connection.createStatement();
//            String sql = "SELECT * FROM madb2.users WHERE login='" + newUser.getLogin() + "' and password = '" + newUser.getPassword() + "';";
//            boolean userInDatabase = false;
//            ResultSet resultSet = statement.executeQuery(sql);
//
//            while (resultSet.next()) {
//                if (resultSet.getString("login").equals(newUser.getLogin()) & resultSet.getString("password").equals(newUser.getPassword())) {
//                    userInDatabase = true;
//                }
//
//            }
//            logger.debug(sql);
//            System.out.println("Есть ли такой User в базе данных: " + userInDatabase);
//            return userInDatabase;
//        } catch (SQLException e) {
//            logger.error("Can't get user by name " + newUser.getLogin(), e);
//        }
//        return false;
//    }

//    public List<User> getUsers() {
//        List<User> list = new ArrayList<>();
//        try {
//            Statement statement = connection.createStatement();
//            String sql = "SELECT * FROM madb2.users";
//            ResultSet resultSet = statement.executeQuery(sql);
//
//            while (resultSet.next()) {
//                String login = resultSet.getString("login");
//                String password = resultSet.getString("password");
//                User user = new User(login, password);
//                list.add(user);
//            }
//            logger.debug(sql);
//        } catch (SQLException e) {
//            logger.error("Can't get all users with madb.users", e);
//        }
//
//        return list;
//    }

//    public void delUser(User user) {
//        try {
//            String sql = "DELETE FROM madb2.users WHERE login='" + user.getLogin()+"' and password = '"+ user.getPassword()+"';";
//            Statement statement = connection.createStatement();
//            statement.executeUpdate(sql);
//            logger.debug(sql);
//        }
//        catch (SQLException e) {
//            logger.error("Can't dell user with madb2.users with name:" + user.getLogin(), e);
//        }
//    }

//    public void editUser(String login, String password) {
//        try {
//            String sql = "UPDATE users SET password = '" + password + "' WHERE login='" + login + "';";
//            Statement statement = connection.createStatement();
//            statement.executeUpdate(sql);
//            logger.debug(sql);
//        }
//        catch (SQLException e) {
//            logger.error("Can't edit password user with madb2.users with name:" + login + " password: " + password, e);
//        }
//    }
}


