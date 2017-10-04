package math.business;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Human Booster on 08/09/2017.
 */
@Entity
public class Choice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String text;
    @OneToMany(mappedBy = "choice")
    private List<Answer> answers;
    @ManyToOne
    private Question question;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Choice{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
