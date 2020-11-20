package engine.util;

import engine.dto.CompletionDto;
import engine.dto.QuizDto;
import engine.exception.InvalidAnswerOptions;
import engine.model.Completion;
import engine.model.Option;
import engine.model.Quiz;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class Utils {

    private Utils() { }

    public static void checkAnswerOptions(QuizDto quiz) {
        int numberOfOptionsInQuiz = quiz.getOptions().size();
        quiz.getAnswer().forEach(answerOptionIdx -> {
            if (answerOptionIdx < 0 || answerOptionIdx >= numberOfOptionsInQuiz) {
                throw new InvalidAnswerOptions();
            }
        });
    }

    public static Quiz convertQuizDtoToEntity(QuizDto quizDto) {
        var quiz = new Quiz();
        quiz.setId(quizDto.getId());
        quiz.setTitle(quizDto.getTitle());
        quiz.setText(quizDto.getText());

        var options = new ArrayList<Option>();
        for (var i = 0; i < quizDto.getOptions().size(); i++) {
            var option = new Option();
            option.setText(quizDto.getOptions().get(i));
            option.setCorrect(quizDto.getAnswer().contains(i));
            option.setPosition(i);
            options.add(option);
        }

        quiz.setOptions(options);

        return quiz;
    }

    public static QuizDto convertQuizEntityToDtoWithoutAnswer(Quiz quiz) {
        var quizDto = new QuizDto();
        quizDto.setId(quiz.getId());
        quizDto.setTitle(quiz.getTitle());
        quizDto.setText(quiz.getText());
        quizDto.setOptions(quiz.getOptions().stream()
                .map(Option::getText)
                .collect(Collectors.toList()));
        return quizDto;
    }

    public static Set<Integer> getCorrectOptionsIndexes(List<Option> options) {
        var indexes = new HashSet<Integer>();
        for (int i = 0; i < options.size(); i++) {
            if (options.get(i).getCorrect()) {
                indexes.add(i);
            }
        }
        return indexes;
    }

    public static CompletionDto convertCompletionEntityToDto(Completion completion) {
        var completionDto = new CompletionDto();
        completionDto.setQuizId(completion.getQuiz().getId());
        completionDto.setQuizTitle(completion.getQuiz().getTitle());
        completionDto.setCompletedAt(completion.getCompletedAt());
        return completionDto;
    }
}