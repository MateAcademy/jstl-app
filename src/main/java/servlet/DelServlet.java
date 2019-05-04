package servlet;

import dao.UserDao;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/delete")
public class DelServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        String name = req.getParameter("name");
        System.out.println();
        System.out.println(name);

        String password = req.getParameter("password");
        System.out.println(password);

        User user = new User(name, password);

        userDao.delUser(user);

        List<User> list = userDao.getUsers();

        req.setAttribute("users", list);
        req.getRequestDispatcher("EditeDelite.jsp").forward(req, resp);
    }
}
