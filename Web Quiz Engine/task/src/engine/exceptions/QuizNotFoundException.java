package engine.exceptions;

public class QuizNotFoundException extends RuntimeException {

    public QuizNotFoundException(int id) {

        super("Could not find quiz with id: " + id);
    }


}
