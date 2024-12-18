package mk.ukim.finki.lab.model.exceptions;

public class InvalidUserCredentialException extends RuntimeException {
    public InvalidUserCredentialException() {
        super("Invalid user credentials exception");
    }
}
