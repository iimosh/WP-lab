package mk.ukim.finki.lab.model.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String username) {
        super(String.format("User %s not found", username));
    }
}
