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

@WebServlet(name = "Edit2Servlet", value = "/edit2")
public class Edit2Servlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        UserDao userDao = new UserDao();
        userDao.editUser(password, login);
        List<User> list = userDao.getUsers();

        request.setAttribute("users", list);
        request.getRequestDispatcher("EditeDelite.jsp").forward(request, response);
    }
}
