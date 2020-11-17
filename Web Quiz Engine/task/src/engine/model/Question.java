package engine.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Question {

    private final int id;
    private final String title;
    private final String text;
    private final String[] options;
    private final int answer;

    public Question(@JsonProperty("id") int id,
                    @JsonProperty("title") String title,
                    @JsonProperty("text") String text,
                    @JsonProperty("options") String[] options,
                    @JsonProperty("answer") int answer) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String[] getOptions() {
        return options;
    }

    public int getAnswer() {
        return answer;
    }
}
