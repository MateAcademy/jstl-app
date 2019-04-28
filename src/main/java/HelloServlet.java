import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "HelloServlet", value = "/hello", loadOnStartup = 1) //при преходе на хелло срабатывает метод сервис
public class HelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");       //даем понять браузеру что ему приходит не картинка не видео не джейсон
        resp.setCharacterEncoding("UTF-8");

        UserRegistated userRegistated = new UserRegistated();

        HttpSession session = req.getSession();
        ServletContext servletContext = req.getServletContext();

        PrintWriter out = resp.getWriter();  //это мы запрос печатаем на странице

        String firstName = req.getParameter("name");
        String login = req.getParameter("login");

        User alone = new User(firstName, login);
        boolean reg = UserRegistated.proverka(alone);

        if (reg) { req.getRequestDispatcher("/web/afterRegister1.jsp").forward(req, resp);
//            out.print(
//                    "<html> " +
//                    "  <head charset=\"utf-8\">\n" +
//                    "    <title>Web application Мэйт Академии</title>\n" +
//                    "  </head>\n" +
//                    "  <body style=\"background-image:url(girl.jpg)\">\n </html>");
            //           out.print("You already have account, just sign in");
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

                req.setAttribute("name", firstName);
                req.setAttribute("login", login);
                req.setAttribute("agree", agree);
                req.setAttribute("method", req.getMethod());
                req.setAttribute("sessionUser", session.getAttribute("sessionUser"));
                req.setAttribute("servletContext", servletContext.getAttribute("name"));

                req.getRequestDispatcher("/web/afterRegister2.jsp").forward(req, resp);

//                out.print("<html> " +
//                        "  <head charset=\"utf-8\">\n" +
//                        "    <title>Web application Мэйт Академии</title>\n" +
//                        "  </head>\n" +
//                        "  <body style=\"background-image:url(girl.jpg)\">\n </html>");
//                out.print("Name " + firstName + "<br>");
//                out.print("Пароль " + login + "<br>");
//                out.print("Согласен ли ты с политикой обработки данных: " + agree + "<br>");
//                out.println("Мой первый servlet, " + "метод: " + req.getMethod() + "<br>");
//                out.println("session account: " + session.getAttribute("sessionUser") + "<br>");
//                out.println("servletContext usera: " + servletContext.getAttribute("name") + "<br>");
//
                session.setMaxInactiveInterval(60);
                userRegistated.getList();
                System.out.println();
            }
        }

//        req.getRequestDispatcher("afterRegister.jsp").forward(req, resp);
//        resp.sendRedirect("http://gogle.com");  //это редирект
    }
}
