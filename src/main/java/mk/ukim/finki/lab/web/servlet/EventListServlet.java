//package mk.ukim.finki.lab.web.servlet;
//
//import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import mk.ukim.finki.lab.model.Event;
//import mk.ukim.finki.lab.model.EventBooking;
//import mk.ukim.finki.lab.service.EventBookingService;
//import mk.ukim.finki.lab.service.EventService;
//import org.thymeleaf.context.WebContext;
//import org.thymeleaf.spring6.SpringTemplateEngine;
//import org.thymeleaf.web.IWebExchange;
//import org.thymeleaf.web.servlet.JakartaServletWebApplication;
//
//import java.io.IOException;
//import java.util.List;
//
//@WebServlet(name="EventListServlet", urlPatterns = "")
//public class EventListServlet extends HttpServlet {
//    private final EventService eventService;
//    private final EventBookingService eventBookingService;
//    private final SpringTemplateEngine springTemplateEngine;
//
//    public EventListServlet(EventService eventService, EventBookingService eventBookingService, SpringTemplateEngine springTemplateEngine) {
//        this.eventService = eventService;
//        this.eventBookingService = eventBookingService;
//        this.springTemplateEngine = springTemplateEngine;
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        IWebExchange webExchange= JakartaServletWebApplication.buildApplication(getServletContext())
//                .buildExchange(req, resp);
//        WebContext context=new WebContext(webExchange);
//
//        String name = req.getParameter("name");
//        List<EventBooking> bookedEvents=eventBookingService.getBookingsByName(name);
//
//        List<Event > events=eventService.listAll();
//        Object search = req.getParameter("search");
//        Object rating = req.getParameter("rating");
//        if(search != null && rating != null ) {
//            if(!search.toString().isEmpty() && !rating.toString().isEmpty()) {
//                events=eventService.searchEvents(search.toString(),Float.parseFloat(rating.toString()));
//            }
//            else if(!search.toString().isEmpty() ){
//            events=eventService.searchEventsByName(search.toString());
//        } else {
//                events=eventService.searchEventsByRating(Float.parseFloat(rating.toString()));
//            }
//        }
//        //context.setVariable("error", true);
//        context.setVariable("events",events );
//
//        springTemplateEngine.process("listEvents.html", context, resp.getWriter());
//
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//            RequestDispatcher dispatcher = request.getRequestDispatcher("/eventBooking");
//            dispatcher.forward(request, response);
//
//    }
//}
