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
import java.io.PrintWriter;

@WebServlet(value = "/hello2", loadOnStartup = 2) //это значение ссылки по которой сервлет срабатывает
public class Hello2Servlet extends HttpServlet {

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
        Registration userRegistated = new Registration();
        userRegistated.getList();
        boolean rez = userRegistated.proverka(user);
        System.out.println("Это проверка, есть ли такой пользователь в базе данных: " + rez);
        System.out.println();
        if (rez == true) {
            req.setAttribute("login", login);
            req.setAttribute("sessionUser", session.getAttribute("sessionUser"));
            req.getRequestDispatcher("3.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("4.jsp").forward(req, resp);
        }
    }
}
