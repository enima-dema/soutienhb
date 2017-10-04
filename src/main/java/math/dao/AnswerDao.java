package math.dao;

import math.business.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerDao extends JpaRepository<Answer, Long> {

    public List<Answer> findAllByUserId(int userId);
}
