package math.dao;

import math.business.Answer;
import math.business.Choice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChoiceDao extends JpaRepository<Choice, Integer> {

}
