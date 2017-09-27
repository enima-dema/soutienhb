package math.service;

import math.business.Question;
import math.dao.QuestionDao;
import math.utils.DataConnect;

import java.sql.SQLException;

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
