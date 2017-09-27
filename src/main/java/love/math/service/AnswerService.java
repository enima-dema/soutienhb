package love.math.service;

import love.math.business.Answer;
import love.math.dao.AnswerDao;
import love.math.dao.UserDao;
import love.math.utils.DataConnect;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Human Booster on 20/09/2017.
 */
public class AnswerService {

    private AnswerDao answerDao;

    public AnswerService() {
        try {
            answerDao = new AnswerDao(DataConnect.getConnection());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Answer> getAllAnswerFromUser(int idUser) {
        return answerDao.getAllAnswerFromUser(idUser);
    }

}
