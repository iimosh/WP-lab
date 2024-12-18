package mk.ukim.finki.lab.service;

import mk.ukim.finki.lab.model.Event;
import mk.ukim.finki.lab.model.Location;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

public interface EventService {
    List<Event> listAll();
    List<Event> searchEvents(String text, float rating);
    List<Event> searchEventsByName(String text);
    Optional<Event> searchEventsById(Long id);
    Optional<Event> save(String name, String description, double rating, Long location);
    List<Event> searchEventsByRating(float rating);
    void deleteEvent(Long id);
    Optional<Event> findEventByName(String name);
    List<Event> findAllByLocationId(Long locationId);

}