package engine.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class QuestionNoAnswer
{
    private final int id;
    private final String title;
    private final String text;
    private final String[] options;

    public QuestionNoAnswer(@JsonProperty("id") int id,
                            @JsonProperty("title") String title,
                            @JsonProperty("text") String text,
                            @JsonProperty("options") String[] options) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.options = options;
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
}
