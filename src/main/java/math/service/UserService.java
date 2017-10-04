package math.service;


import math.business.User;
import math.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Human Booster on 20/09/2017.
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User getOne(int id, User user) {
        return userDao.findOne(id);
    }

    public User getOne(int id) {
        return userDao.findOne(id);
    }

    public User getOne(String login) {
        return userDao.findUserByLogin(login);
    }

    public List<User> getAllExceptLoggedUser(int id, User user) {
        return userDao.findAllExceptUser(id);
    }
}
