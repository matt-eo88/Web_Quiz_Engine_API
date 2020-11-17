package engine.dao;

import engine.exceptions.QuizNotFoundException;
import engine.model.Answer;
import engine.model.Question;
import engine.model.QuestionNoAnswer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository("dao")
public class QuestionDataAccessService implements QuizDao {

    private static final List<Question> DB = new ArrayList<>();

    @Override
    public List<QuestionNoAnswer> getAllQuestions()
    {
        return DB.stream()
                .map(question -> new  QuestionNoAnswer(
                        question.getId(),
                        question.getTitle(),
                        question.getText(),
                        question.getOptions()))
                .collect(Collectors.toList());
    }

    @Override
    public Answer getAnswer(int id, int[] answer) {
        final Question q = getQuestionWithAnswer(id);
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

        int id = DB.size() + 1;
        DB.add(new Question(
                id,
                question.getTitle(),
                question.getText(),
                question.getOptions(),
                question.getAnswer()
        ));

        Question newQuestion = DB.get(DB.size()-1);
        return new QuestionNoAnswer(
                newQuestion.getId(),
                newQuestion.getTitle(),
                newQuestion.getText(),
                newQuestion.getOptions());
    }

    @Override
    public QuestionNoAnswer getQuestionById(int id){
        Question q = getQuestionWithAnswer(id);
        if (q == null) {
            throw new QuizNotFoundException(id);
        }

        return new QuestionNoAnswer(q.getId(),
                q.getTitle(), q.getText(),
                q.getOptions());
    }

    private Question getQuestionWithAnswer(int id) {
        for (Question q : DB) {
            if (q.getId() == id) {
                return q;
            }
        }
        return null;
    }
}
