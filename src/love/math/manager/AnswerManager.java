package love.math.manager;

import love.math.business.Answer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Human Booster on 08/09/2017.
 */
public class AnswerManager {
    private Connection con;
    private ChoiceManager cm;
    private UserManager um;
    private final static int ID_INDEX = 1;
    private final static int ID_USER_INDEX = 2;
    private final static int ID_CHOICE_INDEX = 3;

    public AnswerManager(Connection connection) {
        this.con = connection;
        cm = new ChoiceManager(connection);
        um = new UserManager(connection);
    }

    public Answer getAnswer(int id) {
        String query = "SELECT * FROM answer WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return getAnswerFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Answer> getAllAnswerFromUser(int idUser) {
        List<Answer> answers = new ArrayList<>();
        String query = "SELECT * FROM answer WHERE id_user = ?";
        try {
            PreparedStatement s = con.prepareStatement(query);
            s.setInt(1, idUser);
            ResultSet rs = s.executeQuery();
            while (rs.next()) {
                answers.add(getAnswerFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answers;
    }

    private Answer getAnswerFromResultSet(ResultSet rs) {
        Answer answer = new Answer();
        try {
           answer.setId(rs.getInt(ID_INDEX));
           answer.setChoice(cm.getChoice(rs.getInt(ID_CHOICE_INDEX)));
           answer.setUser(um.getUser(rs.getInt(ID_USER_INDEX)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answer;
    }

    public Answer addAnswer(Answer answer){
        String query = "INSERT INTO answer(id_user, id_choice) VALUES (?, ?)";
        try{
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, answer.getUser().getId());
            ps.setInt(2, answer.getChoice().getId());
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return answer;
    }



}
