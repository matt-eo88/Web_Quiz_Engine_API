package engine.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class QuizNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(QuizNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String quizNotFoundHandler(QuizNotFoundException e)
    {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(EmailAlreadyInUseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String emailAlreadyInUseHandler(EmailAlreadyInUseException e) {
        return e.getMessage();
    }
}
