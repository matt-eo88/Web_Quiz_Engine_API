package engine.dao;

import engine.exceptions.QuizNotFoundException;
import engine.model.Answer;
import engine.model.Question;
import engine.model.QuestionNoAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository("dao")
public class QuestionDataAccess implements QuizDao {

    @Autowired
    private QuestionRepository repository;

    @Override
    public List<QuestionNoAnswer> getAllQuestions()
    {   List<QuestionNoAnswer> allQuestions = new ArrayList<>();

        repository.findAll().
                forEach(question -> allQuestions.add(new  QuestionNoAnswer(
                        question.getId(),
                        question.getTitle(),
                        question.getText(),
                        question.getOptions())));

        return allQuestions;
    }

    @Override
    public Answer getAnswer(int id, int[] answer) {
        final Question q = getQuestionWithAnswer(id).orElseThrow(()
        -> new QuizNotFoundException(id));

        int[] correctAnswer = q.getAnswer();

        if (correctAnswer == null) {
            correctAnswer = new int[0];
        }

        if (Arrays.equals(answer, correctAnswer)) {
            return new Answer(true, "You are correct!");
        } else if (!Arrays.equals(answer, correctAnswer)) {
            return new Answer(false, "You are not correct!");
        } else {
            throw new QuizNotFoundException(id);
        }
    }

    @Override
    public QuestionNoAnswer insertQuestion(Question question)
    {
        if ((question.getOptions() == null || question.getOptions().length < 2)
        || (question.getText() == null || question.getText().isEmpty()) ||
                (question.getTitle() == null || question.getTitle().isEmpty())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        int id = (int) repository.count() + 1;
        repository.save(new Question(
                id,
                question.getTitle(),
                question.getText(),
                question.getOptions(),
                question.getAnswer()
        ));

        Optional<Question> newQ = repository.findById(id);
        Question newQuestion = newQ.orElseThrow(() -> new QuizNotFoundException(id));
        return new QuestionNoAnswer(
                newQuestion.getId(),
                newQuestion.getTitle(),
                newQuestion.getText(),
                newQuestion.getOptions());
    }

    @Override
    public QuestionNoAnswer getQuestionById(int id){
        Optional<Question> question = getQuestionWithAnswer(id);
        Question q = question.orElse(question.orElseThrow(() ->
                new QuizNotFoundException(id)));

        return new QuestionNoAnswer(q.getId(),
                q.getTitle(), q.getText(),
                q.getOptions());
    }

    private Optional<Question> getQuestionWithAnswer(int id) {
        return repository.findById(id);
    }

}
