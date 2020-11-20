package engine.model;

import javax.persistence.*;

@Entity
@Table(name = "option")
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "option_seq")
    private Long id;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private Boolean correct;

    private int position;

    public static Option newOption(String text, boolean correct) {
        var option = new Option();
        option.setText(text);
        option.setCorrect(correct);
        return option;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Option{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", correct=" + correct +
                ", position=" + position +
                '}';
    }
}
