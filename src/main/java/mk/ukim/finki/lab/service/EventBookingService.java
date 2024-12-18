package mk.ukim.finki.lab.service;

import mk.ukim.finki.lab.model.Event;
import mk.ukim.finki.lab.model.EventBooking;

import java.util.List;
import java.util.Optional;

public interface EventBookingService{


    Optional<EventBooking> save(String eventName, String attendeeName, String attendeeAddress, Long numberOfTickets);


    List<EventBooking> getBookingsByName(Long id);
}
