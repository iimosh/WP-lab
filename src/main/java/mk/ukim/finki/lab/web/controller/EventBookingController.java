package mk.ukim.finki.lab.web.controller;


import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.lab.model.User;
import mk.ukim.finki.lab.service.EventBookingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/event-booking")
public class EventBookingController {
    private final EventBookingService eventBookingService;

    public EventBookingController(EventBookingService eventBookingService) {
        this.eventBookingService = eventBookingService;
    }

    @GetMapping
    public String showEventBooking() {
        return "redirect:/events";
    }

    @PostMapping
    public String bookEvent(HttpServletRequest req, Model model) {
        String name=req.getRemoteUser();
        model.addAttribute("name",name);
        String ip=req.getRemoteAddr();
        model.addAttribute("ip",ip);
        String event=req.getParameter("selected");
        model.addAttribute("event",event);
        String br=req.getParameter("numTickets");
        model.addAttribute("br",br);
        if(name==null||event==null||br==null||name.isEmpty()||event.isEmpty()||br.isEmpty()){
            return "redirect:/events?error=NullFields";

        }
        else {
            //req.getSession().setAttribute("error",false);
            eventBookingService.save(event, name, ip, Long.parseLong(br));
            return "bookingConfirmation";
            //templateEngine.process("bookingConfirmation.html", context, resp.getWriter());
        }

    }
}
