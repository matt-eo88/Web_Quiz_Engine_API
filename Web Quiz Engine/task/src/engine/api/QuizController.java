package engine.api;

import engine.model.Answer;
import engine.model.Question;
import engine.model.QuestionNoAnswer;
import engine.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/api/quizzes")
@RestController
public class QuizController
{
    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService)
    {
        this.quizService = quizService;
    }

    @GetMapping
    public List<QuestionNoAnswer> getAllQuestions()
    {
        return quizService.getAllQuestions();
    }

    @GetMapping(path = "{id}")
    public QuestionNoAnswer getQuestionById(@PathVariable int id)
    {
        return quizService.getQuestionById(id);
    }

    @PostMapping(path = "{id}/solve")
    public Answer getAnswerResponse(@PathVariable("id") int id, @RequestParam(name = "answer") Integer answer)
    {
        return quizService.getAnswer(id, answer);
    }

    @PostMapping
    public QuestionNoAnswer createQuestion(@RequestBody Question question)
    {
        return quizService.insertQuestion(question);
    }

}
