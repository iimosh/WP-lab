//package mk.ukim.finki.lab.repository.impl;
//
//import mk.ukim.finki.lab.bootstrap.DataHolder;
//import mk.ukim.finki.lab.model.EventBooking;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public class InMemoryEventBookingRepository {
//    public EventBooking place_booking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets){
//        EventBooking booking=new EventBooking(eventName,attendeeName,attendeeAddress,(long)numberOfTickets);
//        DataHolder.bookedEvents.add(booking);
//        return booking;
//    }
//    public List<EventBooking> getBookings(String name){
//        return DataHolder.bookedEvents.stream().filter(b->b.getEventName().equals(name)).toList();
//    }
//}
