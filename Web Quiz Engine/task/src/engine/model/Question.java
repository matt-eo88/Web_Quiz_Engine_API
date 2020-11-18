package engine.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "questions")
public class Question {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String title;
    @Column
    private String text;
    @Column
    private String[] options;
    @Column
    private int[] answer;

    public Question(@JsonProperty("id") int id,
                    @JsonProperty("title") String title,
                    @JsonProperty("text") String text,
                    @JsonProperty("options") String[] options,
                    @JsonProperty("answer") int[] answer) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
    }

    public Question() {}


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

    public int[] getAnswer() {
        return answer;
    }
}
