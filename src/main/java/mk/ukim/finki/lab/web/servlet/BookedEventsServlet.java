//package mk.ukim.finki.lab.web.servlet;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import mk.ukim.finki.lab.model.EventBooking;
//import mk.ukim.finki.lab.service.EventBookingService;
//import org.thymeleaf.context.WebContext;
//import org.thymeleaf.spring6.SpringTemplateEngine;
//import org.thymeleaf.web.IWebExchange;
//import org.thymeleaf.web.servlet.JakartaServletWebApplication;
//
//import java.io.IOException;
//import java.util.List;
//
//@WebServlet(name="BookedEvents", urlPatterns = "/bookedEvents")
//public class BookedEventsServlet extends HttpServlet {
//    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//    EventBookingService eventBookingService;
//
//    public BookedEventsServlet(SpringTemplateEngine templateEngine, EventBookingService eventBookingService) {
//        this.templateEngine = templateEngine;
//        this.eventBookingService = eventBookingService;
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        IWebExchange webExchange= JakartaServletWebApplication.buildApplication(getServletContext())
//                .buildExchange(req, resp);
//        WebContext context=new WebContext(webExchange);
//        Object name = req.getSession().getAttribute("name");
////        List<EventBooking> events=eventBookingService.getBookingsByName(name.toString());
////        if(!events.isEmpty()){
////            context.setVariable("events", events);
////            context.setVariable("name", name);
////        templateEngine.process("bookedEvents.html", context, resp.getWriter());}
////        else{ resp.sendRedirect("");}
//
//    }
//}
