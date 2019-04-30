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
import java.io.PrintWriter;

@WebServlet( value = "/hello", loadOnStartup = 1) //при преходе на хелло срабатывает метод сервис
public class HelloServlet extends HttpServlet {

    private static final UserDao userDao = new UserDao();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");       //даем понять браузеру что ему приходит не картинка не видео не джейсон
        resp.setCharacterEncoding("UTF-8");

        Registration userRegistated = new Registration();

        HttpSession session = req.getSession();
        ServletContext servletContext = req.getServletContext();

        PrintWriter out = resp.getWriter();  //это мы запрос печатаем на странице

        String name = req.getParameter("name");
        String password = req.getParameter("password");

        System.out.println("Имя: " + name);
        System.out.println("Пароль: " + password);

        User newUser = new User(name, password);
        boolean reg = Registration.proverka(newUser);

        if (reg) {
            req.setAttribute("name", name);
            req.setAttribute("login", password);
            req.getRequestDispatcher("1.jsp").forward(req, resp);

        } else {

            userDao.addUser(newUser);

            if (session.getAttribute("sessionUser") == null) {
                session.setAttribute("sessionUser", name);
                servletContext.setAttribute("name", name );
            }

            userRegistated.addNewUsers(newUser);

            resp.setStatus(HttpServletResponse.SC_OK);

            if (req.getMethod().equals("GET")) {
                System.out.println("Мы не поддерживаем работу с методом GET");
                out.print("Мы не поддерживаем работу с методом GET");
            } else {
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
                userRegistated.getList();
                System.out.println();
            }
        }

//        req.getRequestDispatcher("afterRegister.jsp").forward(req, resp); //куда какие страницы отправлять, куда какие запросы показывать
//          resp.sendRedirect("afterRegister.jsp")
//        resp.sendRedirect("http://gogle.com");  //это редирект, без всякой обработки отпраивить
    }
}
