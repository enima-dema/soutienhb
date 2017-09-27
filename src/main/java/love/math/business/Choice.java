package love.math.business;

/**
 * Created by Human Booster on 08/09/2017.
 */
public class Choice {
    private int id;
    private String text;

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

    @Override
    public String toString() {
        return "Choice{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
