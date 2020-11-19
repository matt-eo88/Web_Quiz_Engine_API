package engine.service;

import engine.dao.UserDao;
import engine.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserDao userDao;

    @Autowired
    public UserService(@Qualifier("uDao") UserDao userDao) {
        this.userDao = userDao;
    }

    public void insertUser(User user) {
        userDao.insertUser(user);
    }

    public void deleteUserById(int id) {
        userDao.deleteUserById(id);
    }
}
