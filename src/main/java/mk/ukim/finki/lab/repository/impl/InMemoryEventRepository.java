package mk.ukim.finki.lab.repository.impl;

import mk.ukim.finki.lab.bootstrap.DataHolder;
import mk.ukim.finki.lab.model.Event;
import mk.ukim.finki.lab.model.Location;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryEventRepository {
    public Optional<Event> findById(Long id) {
        return DataHolder.events.stream().
                filter(e -> e.getId().equals(id)).findFirst();
    }
    public List<Event> findAll(){
        return DataHolder.events;
    }
    public Optional<Event> save(String name, String description, double popularityScore, Location location) {
        DataHolder.events.removeIf(i-> i.getName().equals(name));
        Event event = new Event(name, description, popularityScore, location);
        DataHolder.events.add(event);
        return Optional.of(event);
    }
    public void deleteById(Long id) {
        DataHolder.events.removeIf(i-> i.getId().equals(id));
    }
    public List<Event> searchEvents(String text,float rating){
        return DataHolder.events.stream().filter(d->d.getName().contains(text)&&d.getPopularityScore()>=rating).toList();
    }
    public List<Event> searchEventsByName(String text){
        return DataHolder.events.stream().filter(d->d.getName().contains(text)).toList();
    }
    public List<Event> searchEventsByRating(float rating){
        return DataHolder.events.stream().filter(d->d.getPopularityScore()>=rating).toList();
    }
}
