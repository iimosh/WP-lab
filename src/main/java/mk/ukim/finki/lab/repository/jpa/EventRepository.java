package mk.ukim.finki.lab.repository.jpa;

import mk.ukim.finki.lab.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByLocationId(Long locationId);
    List<Event> findAllByNameAndPopularityScore(String name, double popularityScore);
    List<Event> findAllByPopularityScore(double popularityScore);

    List<Event> findAllByName(String text);
    Optional<Event> findByName(String name);

    void deleteByName(String name);

}
