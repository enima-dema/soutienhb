package math.dao;

import math.business.Choice;
import math.business.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionDao extends JpaRepository<Question, Integer> {

    @Query("SELECT q, c FROM Question q, Choice c WHERE c.question.id = q.id AND c.id = :choiceId")
    Question findByChoice(@Param("choiceId") int choiceId);
}
