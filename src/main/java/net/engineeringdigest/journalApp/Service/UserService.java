package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User saveUser(User user) {
        return repository.save(user);
    }

    public User findByUsername(String username) {
        return repository.findByUsername(username).orElse(null);
    }

    public User register(User user) {
        return repository.save(user);
    }

    public User login(String username, String password) {

        // ✅ FIXED LINE
        User user = repository.findByUsername(username).orElse(null);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }

        return null;
    }
}