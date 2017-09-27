package math.dao;


import math.business.Answer;
import math.business.User;
import math.service.AnswerService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Human Booster on 08/09/2017.
 */
public class UserDao {
    private Connection con;
    private final static int ID_INDEX = 1;
    private final static int NAME_INDEX = 2;
    private final static int LASTNAME_INDEX = 3;
    private final static int LOGIN_INDEX = 4;
    private final static int PASSWORD_INDEX = 5;
    private final static int SEX_INDEX = 6;
    private final static int DESCRIPTION_INDEX = 7;
    private final static int BIRTHDAY_INDEX = 8;

    public UserDao(Connection connection) {
        this.con = connection;
    }

    public User getUser(int id) {
        String query = "SELECT * FROM user WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return getUserFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUser(int id, User user) {
        String query = "SELECT * FROM user WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return getUserFromResultSet(rs, user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUser(String login) {
        String query = "SELECT * FROM user WHERE login = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return getUserFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM user";
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                users.add(getUserFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public List<User> getAllUsersExceptLoggedUser(int id, User user) {
        List<User> users = new ArrayList<>();
        System.out.println("TEST ID");
        System.out.println(id);
        String query = "SELECT * FROM user WHERE NOT user.id = ?";
        try {
            PreparedStatement s = con.prepareStatement(query);
            s.setInt(1, id);
            ResultSet rs = s.executeQuery();
            while (rs.next()) {
                users.add(getUserFromResultSet(rs, user));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    private User getUserFromResultSet(ResultSet rs) {
        User user = new User();
        try {
            user.setId(rs.getInt(ID_INDEX));
            user.setName(rs.getString(NAME_INDEX));
            user.setLastname(rs.getString(LASTNAME_INDEX));
            user.setLogin(rs.getString(LOGIN_INDEX));
            user.setPassword(rs.getString(PASSWORD_INDEX));
            user.setSexe(rs.getString(SEX_INDEX));
            user.setDescription(rs.getString(DESCRIPTION_INDEX));
            user.setBirthday(rs.getDate(BIRTHDAY_INDEX));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    private User getUserFromResultSet(ResultSet rs, User user2) {
        User user = getUserFromResultSet(rs);
        user.setCompatibility(String.valueOf((int) getComptability(user, user2)) + "%");
        return user;
    }

    private static float getComptability(User user1, User user2) {
        AnswerService answerService = new AnswerService();
        List<Answer> answersUser1 = answerService.getAllAnswerFromUser(user1.getId());
        List<Answer> answersUser2 = answerService.getAllAnswerFromUser(user2.getId());
        if (answersUser1.size() > answersUser2.size()) {
            return compareTwoAnswers(answersUser2, answersUser1)/answersUser1.size()*100;
        } else {
            return compareTwoAnswers(answersUser1, answersUser2)/answersUser2.size()*100;
        }
    }

    private static float compareTwoAnswers(List<Answer> answers, List<Answer> answersToCompare) {
        float numberOfSameAnswer = 0;
        for (Answer answer : answers) {
            float x = 0;
            for (Answer answerToCompare : answersToCompare) {
                if (answer.getChoice().getId() == answerToCompare.getChoice().getId()) {
                    ++x;
                }
            }
            if (x > 0) {
                ++numberOfSameAnswer;
            }
        }
        return numberOfSameAnswer;
    }

    public User createUser(User user){
        String query = "INSERT INTO USER(name, lastname, login, password, sex, description, birthday) VALUES " +
                "(?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, user.getName());
            ps.setString(2, user.getLastname());
            ps.setString(3, user.getLogin());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getSexe());
            ps.setString(6, user.getDescription());
            ps.setDate(7, utilDateToSqlDate(user.getBirthday()));
            ps.executeUpdate();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Date utilDateToSqlDate(java.util.Date date){
        return new Date(date.getTime());
    }
}
