package mk.ukim.finki.lab.web.controller;

import mk.ukim.finki.lab.model.Event;
import mk.ukim.finki.lab.model.EventBooking;
import mk.ukim.finki.lab.model.Location;
import mk.ukim.finki.lab.service.EventBookingService;
import mk.ukim.finki.lab.service.EventService;
import mk.ukim.finki.lab.service.LocationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;
    private final LocationService locationService;
    private final EventBookingService eventBookingService;

    public EventController(EventService eventService, LocationService locationService, EventBookingService eventBookingService) {
        this.eventService = eventService;
        this.locationService = locationService;
        this.eventBookingService = eventBookingService;
    }

    @GetMapping
    public String getEventsPage(@RequestParam(required = false)
                                String error,
                                @RequestParam(required = false) String search,
                                @RequestParam(required = false) Double rating,
                                @RequestParam (required = false) Long location,
                                Model model){
        if (error != null&& !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Event> events=eventService.listAll();
        List<Location> locations=locationService.findAll();
        if(search != null && rating != null ) {
            if(!search.isEmpty() && !rating.toString().isEmpty()) {
                events=eventService.searchEvents(search,Float.parseFloat(rating.toString()));
            }
            else if(!search.isEmpty() ){
                events=eventService.searchEventsByName(search);
            } else {
                events=eventService.searchEventsByRating(Float.parseFloat(rating.toString()));
            }
        }
        if(location != null) {
            events=eventService.findAllByLocationId(location);
        }

        model.addAttribute("events",events);
        model.addAttribute("locations", locations);
        return "listEvents";
    }
    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ADMIN')")
    public String addEventPage(Model model) {
        List<Location> locations=locationService.findAll();
        model.addAttribute("locations", locations);
        return "add-event";
    }
    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String saveEvent(@RequestParam String name,
                            @RequestParam String description,
                            @RequestParam double score,
                            @RequestParam Long location
                            ){
        eventService.save(name, description, score, location);
        return "redirect:/events";
    }
    @GetMapping("/edit-event/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editEventPage(Model model, @PathVariable Long id) {
        if(eventService.searchEventsById(id).isPresent()) {
            Event event  = eventService.searchEventsById(id).get();
            List<Location> locations=locationService.findAll();
            model.addAttribute("locations", locations);
            model.addAttribute("event",event);
            return "add-event";
        }
        return "redirect:/events?error=EventNotFound";
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteEvent(@PathVariable Long id) {
    eventService.deleteEvent(id);
    return "redirect:/events";
    }


    @GetMapping("/details/{id}")
    public String detailsEvent(@PathVariable Long id,Model model) {
        if(eventService.searchEventsById(id).isPresent()) {
            Event event  = eventService.searchEventsById(id).get();
            List< EventBooking> bookings =eventBookingService.getBookingsByName(id);
            model.addAttribute("bookings", bookings);
            model.addAttribute("event",event);
            return "details-event";
        }
        return "redirect:/events?error=EventNotFound";

    }
}
