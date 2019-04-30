package servlet;

import dao.UserDao;
import model.User;
import model.Registration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value = "/hello", loadOnStartup = 1) //при преходе на хелло срабатывает метод сервис
public class RegisterServlet extends HttpServlet {

    private static final UserDao userDao = new UserDao();
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        Registration registration = new Registration();

        HttpSession session = req.getSession();
        ServletContext servletContext = req.getServletContext();

        String name = req.getParameter("name");
        String password = req.getParameter("password");

        User newUser = new User(name, password);
        boolean reg = Registration.checkUser(newUser);

        if (reg) {
            req.setAttribute("name", name);
            req.setAttribute("login", password);
            req.getRequestDispatcher("1.jsp").forward(req, resp);

        } else {
            userDao.addUser(newUser);

            if (session.getAttribute("sessionUser") == null) {
                session.setAttribute("sessionUser", name);
                servletContext.setAttribute("name", name);
            }

            registration.addNewUsers(newUser);

            resp.setStatus(HttpServletResponse.SC_OK);

            String agree = req.getParameter("agree");
            if (agree == null) {
                agree = "НЕТ";
            }

            req.setAttribute("agree", agree);
            req.setAttribute("name", name);
            req.setAttribute("login", password);
            req.setAttribute("method", req.getMethod());
            req.setAttribute("sessionUser", session.getAttribute("sessionUser"));
            req.setAttribute("servletContext", servletContext.getAttribute("name"));

            req.getRequestDispatcher("2.jsp").forward(req, resp);

            session.setMaxInactiveInterval(60);
            registration.getList();
            System.out.println();
        }
    }

}
