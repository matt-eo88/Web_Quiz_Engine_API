package engine.dao;

import engine.exceptions.QuizNotFoundException;
import engine.model.Answer;
import engine.model.Question;
import engine.model.QuestionNoAnswer;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    public Answer getAnswer(Integer id, Integer answer) {
        final Optional<Question> questionOptional = getQuestionWithAnswer(id);

        return questionOptional.stream()
                .mapToInt(Question::getAnswer)
                .mapToObj((correctAnswer) -> {
                    Answer correct = new Answer(true, "You are correct!");
                    Answer incorrect = new Answer(false, "You are wrong!");
                    return answer == correctAnswer ? correct : incorrect;
                }).findFirst()
                .orElseThrow(() -> new QuizNotFoundException(id));

    }

    @Override
    public QuestionNoAnswer insertQuestion(Question question)
    {
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
        Optional<Question> questionOptional = getQuestionWithAnswer(id);

        return questionOptional.map(question -> new QuestionNoAnswer(
                question.getId(),
                question.getTitle(),
                question.getText(),
                question.getOptions()
        )).orElseThrow(() -> new QuizNotFoundException(id));

    }

    private Optional<Question> getQuestionWithAnswer(int id) {
        return DB.stream()
                .filter(question -> question.getId() == id)
                .findFirst();
    }
}
