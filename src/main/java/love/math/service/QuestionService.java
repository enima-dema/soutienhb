package love.math.service;

import love.math.business.Question;
import love.math.dao.QuestionDao;
import love.math.utils.DataConnect;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Human Booster on 20/09/2017.
 */
public class QuestionService {

    private QuestionDao questionDao;

    public QuestionService() {
        try {
            questionDao = new QuestionDao(DataConnect.getConnection());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Question getQuestionFromChoice(int idChoice) {
        return questionDao.getQuestionFromChoice(idChoice);
    }
}
