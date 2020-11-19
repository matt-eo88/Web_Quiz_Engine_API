package engine.exceptions;

public class EmailAlreadyInUseException extends RuntimeException {

    public EmailAlreadyInUseException() {
        super("The email you chose has already been taken");
    }
}
