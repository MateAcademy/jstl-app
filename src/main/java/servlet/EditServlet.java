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

@WebServlet(name = "EditServlet", value = "/edit")
public class EditServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        System.out.println();
        System.out.println(name + " !!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        request.setAttribute("name", name);
        request.getRequestDispatcher("Edite.jsp").forward(request, response);
    }
}
