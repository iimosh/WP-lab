package mk.ukim.finki.lab.service.impl;

import mk.ukim.finki.lab.model.Event;
import mk.ukim.finki.lab.model.EventBooking;
import mk.ukim.finki.lab.model.User;
import mk.ukim.finki.lab.model.exceptions.EventNotFoundException;
import mk.ukim.finki.lab.model.exceptions.UserNotFoundException;
import mk.ukim.finki.lab.repository.jpa.EventBookingRepository;
import mk.ukim.finki.lab.repository.jpa.EventRepository;
import mk.ukim.finki.lab.repository.jpa.UserRepository;
import mk.ukim.finki.lab.service.EventBookingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventBookingServiceImpl implements EventBookingService {
    private final EventBookingRepository eventBookingRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public EventBookingServiceImpl(EventBookingRepository eventBookingRepository, UserRepository userRepository, EventRepository eventRepository) {
        this.eventBookingRepository = eventBookingRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public Optional<EventBooking> save(String eventName, String attendeeName, String attendeeAddress, Long numberOfTickets) {
        User user=userRepository.findByUsername(attendeeName)
                .orElseThrow(()->new UserNotFoundException(attendeeName));
        Event event=eventRepository.findByName(eventName)
                .orElseThrow(()->new EventNotFoundException(eventName));
        EventBooking eventBooking=new EventBooking(event,user,numberOfTickets);
        user.getBookings().add(eventBooking);
        return Optional.of(eventBookingRepository.save(eventBooking));
    }

    @Override
    public List<EventBooking> getBookingsByName(Long id) {
        return eventBookingRepository.findAllByEventId(id);
    }


}
