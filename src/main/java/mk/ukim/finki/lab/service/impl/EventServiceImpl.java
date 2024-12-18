package mk.ukim.finki.lab.service.impl;

import jakarta.transaction.Transactional;
import mk.ukim.finki.lab.model.Event;
import mk.ukim.finki.lab.model.Location;
import mk.ukim.finki.lab.model.exceptions.LocationNotFoundException;
import mk.ukim.finki.lab.repository.impl.InMemoryEventRepository;
import mk.ukim.finki.lab.repository.impl.InMemoryLocationRepository;
import mk.ukim.finki.lab.repository.jpa.EventRepository;
import mk.ukim.finki.lab.repository.jpa.LocationRepository;
import mk.ukim.finki.lab.service.EventService;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;

    public EventServiceImpl(EventRepository eventRepository, LocationRepository locationRepository) {
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;
    }


    @Override
    public List<Event> listAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> searchEvents(String text,float rating) {
        return eventRepository.findAllByNameAndPopularityScore(text,rating);
    }

    @Override
    public List<Event> searchEventsByName(String text) {
        return eventRepository.findAllByName(text);
    }

    @Override
    public Optional<Event> searchEventsById(Long id) {
        return eventRepository.findById(id);
    }

    @Override
    @Transactional
    public Optional<Event> save(String name, String description, double rating, Long location) {
        Location loc=locationRepository.findById(location).orElseThrow(()->new LocationNotFoundException(location));
        eventRepository.deleteByName(name);
        return Optional.of(eventRepository.save(new Event(name, description, rating, loc)));
    }

    @Override
    public List<Event> searchEventsByRating(float rating) {
        return eventRepository.findAllByPopularityScore(rating);
    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public Optional<Event> findEventByName(String name) {
        return eventRepository.findByName(name);
    }

    @Override
    public List<Event> findAllByLocationId(Long locationId) {
        return eventRepository.findAllByLocationId(locationId);
    }

}
