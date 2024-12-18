package mk.ukim.finki.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;


@WebServlet(name="Name", urlPatterns = "/name")
public class NameServlet extends HttpServlet {
    SpringTemplateEngine templateEngine;

    public NameServlet(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange= JakartaServletWebApplication.buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext context=new WebContext(webExchange);
        templateEngine.process("name.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!req.getParameter("name").isEmpty()) {
            req.getSession().setAttribute("name", req.getParameter("name"));
            resp.sendRedirect("/bookedEvents");
        }
        else{
            resp.sendRedirect("/name");
        }
    }
}
