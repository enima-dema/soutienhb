package love.math.service;


import love.math.business.User;
import love.math.dao.UserDao;
import love.math.utils.DataConnect;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Human Booster on 20/09/2017.
 */
public class UserService {
    private UserDao userDao;

    public UserService(){
        try {
            userDao = new UserDao(DataConnect.getConnection());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public User getOne(int id, User user){
        return userDao.getUser(id, user);
    }

    public User getOne(String login){
        return userDao.getUser(login);
    }

    public List<User> getAllExceptLoggedUser(int id, User user){
        return userDao.getAllUsersExceptLoggedUser(id, user);
    }

    public User createUser(User user){
        return userDao.createUser(user);
    }
}
