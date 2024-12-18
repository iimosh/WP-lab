package mk.ukim.finki.lab.service;

import mk.ukim.finki.lab.model.Location;

import java.util.List;
import java.util.Optional;

public interface LocationService {
    public List<Location> findAll();
    public Optional<Location> findById(Long id);

}
