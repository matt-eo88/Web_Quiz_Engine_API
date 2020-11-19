package engine.dao;

import engine.model.User;

public interface UserDao {

    void insertUser(User user);

    void deleteUserById(int id);
}
