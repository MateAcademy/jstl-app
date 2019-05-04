import dao.DbConnector;
import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
User user = new User("Sergey","1");
      //  main.delUser(user);

    }

    public void getUser(User newUser) {
        Connection connnection = DbConnector.connect();
        try {

            //       String sql = "SELECT * FROM madb.users WHERE name='"+ newUser.getName() + "' and password = '"+ newUser.getPassword()+ "';";
            String query = "SELECT * FROM madb.users";

            Statement statement = connnection.createStatement();  //что бы выполнить запрос
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
               // System.out.println(name + "  " + password);

                if (newUser.getName().equals(name) & newUser.getPassword().equals(password))
                    System.out.println("Такой User уже есть в БД! ");

            }
        }
            catch (SQLException e) {
                e.printStackTrace();
        }
    }

//    public List<User> getUsers() {
//        List<User> list = new ArrayList<>();
//        Connection connnection = DbConnector.connect();
//        try {
//            Statement statement = connnection.createStatement();
//            String sql = "SELECT * FROM madb.users";
//            ResultSet resultSet = statement.executeQuery(sql);
//
//            while (resultSet.next()) {
//                String name = resultSet.getString("name");
//                String password = resultSet.getString("password");
//                User user = new User(name, password);
//                list.add(user);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return list;
//    }



//    public void delUser(User user) {
//        Connection connnection = DbConnector.connect();
//        try {
//            String query = "DELETE FROM madb.users WHERE name='" + user.getName()+"' and password = '"+user.getPassword()+"';";
//            Statement statement = connnection.createStatement();
//            statement.executeUpdate(query);
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

}
