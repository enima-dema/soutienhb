package love.math.manager;

import love.math.business.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Human Booster on 08/09/2017.
 */
public class QuestionManager {
    private Connection con;
    private ChoiceManager cm;
    private final static int ID_INDEX = 1;
    private final static int TEXT_INDEX = 2;

    public QuestionManager(Connection connection) {
        this.con = connection;
        cm = new ChoiceManager(connection);
    }

    /*public Question getQuestion(int id) {
        String query = "SELECT * FROM question WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return getQuestionFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }*/

    public Question getQuestionFromChoice(int idChoice){
        String query = "SELECT * FROM question q, choice c WHERE c.id_question = q.id AND c.id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, idChoice);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return getQuestionFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Question> getAllQuestion() {
        List<Question> questions = new ArrayList<>();
        String query = "SELECT * FROM question";
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                questions.add(getQuestionFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }

    private Question getQuestionFromResultSet(ResultSet rs) {
        Question question = new Question();
        try {
            question.setId(rs.getInt(ID_INDEX));
            question.setText(rs.getString(TEXT_INDEX));
            question.setChoices(cm.getAllChoiceByQuestion(rs.getInt(ID_INDEX)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return question;
    }
}
