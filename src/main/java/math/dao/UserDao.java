package math.dao;

import math.business.Question;
import math.business.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDao extends JpaRepository<User, Integer> {

    User findUserByLogin(String login);

    @Query("SELECT u FROM User u WHERE NOT u.id = :id ")
    List<User> findAllExceptUser(@Param("id") int id);
}
