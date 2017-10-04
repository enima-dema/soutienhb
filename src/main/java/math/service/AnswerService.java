package math.service;

import math.business.Answer;
import math.dao.AnswerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Human Booster on 20/09/2017.
 */
@Service
public class AnswerService {

    @Autowired
    private AnswerDao answerDao;

    public List<Answer> getAllAnswerFromUser(int idUser) {
        return answerDao.findAllByUserId(idUser);
    }

}
