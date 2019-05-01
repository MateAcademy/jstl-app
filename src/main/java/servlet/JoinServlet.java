package servlet;

import model.User;
import model.Registration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value = "/hello2")
public class JoinServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("Servlet initialized");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");       //даем понять браузеру что ему приходит не картинка не видео не джейсон
        resp.setCharacterEncoding("UTF-8");

        String firstName = req.getParameter("name2");
        String login = req.getParameter("login2");
        System.out.println("мы заходим на сайт под именем:  " + firstName + " и паролем:  " + login);

        User user = new User(firstName, login);
        Registration registration = new Registration();
        registration.getList();
        boolean rez = registration.checkUser(user);
        System.out.println("Это проверка, есть ли такой пользователь в базе данных: " + rez);
        System.out.println();
        if (rez == true) {
            req.setAttribute("login", login);
            req.setAttribute("sessionUser", session.getAttribute("sessionUser"));
            req.getRequestDispatcher("SignIn.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("IncorrectLogPass.jsp").forward(req, resp);
        }
    }
}
