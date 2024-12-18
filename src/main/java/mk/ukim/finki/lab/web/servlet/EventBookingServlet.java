package mk.ukim.finki.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.lab.service.EventBookingService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name="EventBookingServlet",urlPatterns = "/eventBooking")
public class EventBookingServlet extends HttpServlet {
    EventBookingService eventBookingService;
    SpringTemplateEngine templateEngine;

    public EventBookingServlet(SpringTemplateEngine templateEngine,EventBookingService eventBookingService) {
        this.templateEngine = templateEngine;
        this.eventBookingService = eventBookingService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange= JakartaServletWebApplication.buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext context=new WebContext(webExchange);
        String name=req.getParameter("name");
        context.setVariable("name",name);
        String ip=req.getRemoteAddr();
        context.setVariable("ip",ip);
        String event=req.getParameter("selected");
        context.setVariable("event",event);
        String br=req.getParameter("numTickets");
        context.setVariable("br",br);

        if(name==null||event==null||br==null||name.isEmpty()||event.isEmpty()||br.isEmpty()){
            req.getSession().setAttribute("error",true);
            resp.sendRedirect("");

        }
        else {
            req.getSession().setAttribute("error",false);
//            eventBookingService.save(event, name, ip, Long.parseLong(br));
            templateEngine.process("bookingConfirmation.html", context, resp.getWriter());
        }
    }
}
