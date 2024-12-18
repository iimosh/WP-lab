package mk.ukim.finki.lab.repository.impl;

import mk.ukim.finki.lab.bootstrap.DataHolder;
import mk.ukim.finki.lab.model.Location;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryLocationRepository {
    public List<Location> findAll(){
        return DataHolder.locations;
    }

    public Optional<Location> findById(Long id) {
        return DataHolder.locations.stream().filter(location -> location.getId().equals(id)).findFirst();
    }
}
