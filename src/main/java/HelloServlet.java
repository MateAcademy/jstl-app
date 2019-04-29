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


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");       //даем понять браузеру что ему приходит не картинка не видео не джейсон
        resp.setCharacterEncoding("UTF-8");

        UserRegistrated1 userRegistated = new UserRegistrated1();

        HttpSession session = req.getSession();
        ServletContext servletContext = req.getServletContext();

        PrintWriter out = resp.getWriter();  //это мы запрос печатаем на странице

        String firstName = req.getParameter("name");
        String login = req.getParameter("login");

        System.out.println(firstName);
        System.out.println(login);

        User alone = new User(firstName, login);
        boolean reg = UserRegistrated1.proverka(alone);

        if (reg) {
            req.getRequestDispatcher("1.jsp").forward(req, resp);

        } else {
            if (session.getAttribute("sessionUser") == null) {
                session.setAttribute("sessionUser", firstName);
                servletContext.setAttribute("name", firstName );
            }

            userRegistated.addNewUsers(alone);

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
                req.setAttribute("name", firstName);
                req.setAttribute("login", login);
                req.setAttribute("method", req.getMethod());
                req.setAttribute("sessionUser", session.getAttribute("sessionUser"));
                req.setAttribute("servletContext", servletContext.getAttribute("name"));

                req.getRequestDispatcher("2.jsp").forward(req, resp);

                session.setMaxInactiveInterval(60);
                userRegistated.getList();
                System.out.println();
            }
        }

//        req.getRequestDispatcher("afterRegister.jsp").forward(req, resp);
//        resp.sendRedirect("http://gogle.com");  //это редирект
    }
}
