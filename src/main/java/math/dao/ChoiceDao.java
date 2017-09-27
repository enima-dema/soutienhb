package math.dao;



import math.business.Choice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChoiceDao {
    private Connection con;
    private final static int ID_INDEX = 1;
    private final static int TEXT_INDEX = 2;

    public ChoiceDao(Connection connection) {
        this.con = connection;
    }

    public Choice getChoice(int id) {
        String query = "SELECT * FROM choice WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return getChoiceFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Choice> getAllChoiceByQuestion(int idQuestion){
        String query = "SELECT * FROM choice WHERE id_question = ?";
        List<Choice> choices = new ArrayList<>();
        try{
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, idQuestion);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                choices.add(getChoiceFromResultSet(rs));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return choices;
    }


    private Choice getChoiceFromResultSet(ResultSet rs) {
        Choice choice = new Choice();
        try {
            choice.setId(rs.getInt(ID_INDEX));
            choice.setText(rs.getString(TEXT_INDEX));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return choice;
    }

}
