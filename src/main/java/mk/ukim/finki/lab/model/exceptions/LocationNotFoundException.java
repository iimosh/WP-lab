package mk.ukim.finki.lab.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LocationNotFoundException extends RuntimeException {
    public LocationNotFoundException(Long id) {
        super("Could not find location with id " + id);
    }
}
