package math.service;

import math.business.Question;
import math.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Human Booster on 20/09/2017.
 */
@Service
public class QuestionService {

    @Autowired
    private QuestionDao questionDao;

    public Question getQuestionFromChoice(int idChoice) {
        return questionDao.findByChoice(idChoice);
    }
}
