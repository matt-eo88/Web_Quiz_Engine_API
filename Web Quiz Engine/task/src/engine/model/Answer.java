package engine.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Answer
{
    private final boolean success;
    private final String feedback;

    public Answer(@JsonProperty("success") boolean success,
                  @JsonProperty("feedback") String feedback)
    {
        this.success = success;
        this.feedback = feedback;
    }
}
