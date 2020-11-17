package engine.service;

import engine.dao.QuizDao;
import engine.model.Answer;
import engine.model.Question;
import engine.model.QuestionNoAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class QuizService {

    private final QuizDao quizDao;

    @Autowired
    public QuizService(@Qualifier("dao") QuizDao quizDao)
    {
        this.quizDao = quizDao;
    }

    public List<QuestionNoAnswer> getAllQuestions()
    {
        return quizDao.getAllQuestions();
    }

    public Answer getAnswer(int id, int[] answer)
    {
        return quizDao.getAnswer(id, answer);
    }

    public QuestionNoAnswer insertQuestion(Question question)
    {
        return quizDao.insertQuestion(question);
    }

    public QuestionNoAnswer getQuestionById(int id)
    {
        return quizDao.getQuestionById(id);
    }
}
