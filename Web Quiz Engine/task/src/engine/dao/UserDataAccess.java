package engine.dao;

import engine.exceptions.EmailAlreadyInUseException;
import engine.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("uDao")
public class UserDataAccess implements UserDao {

    @Autowired
    private UserRepository repository;

    @Override
    public void insertUser(User user) {
        repository.findAll().forEach(u -> {
           if (u.getEmail().equals(user.getEmail())) {
               throw new EmailAlreadyInUseException();
           }
        });
        repository.save(user);

    }

    @Override
    public void deleteUserById(int id) {
        repository.deleteById(id);
    }
}
