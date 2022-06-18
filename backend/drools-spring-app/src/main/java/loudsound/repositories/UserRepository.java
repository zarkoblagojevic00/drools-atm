package loudsound.repositories;

import loudsound.controllers.dtos.NewUserDTO;
import loudsound.controllers.exceptions.EntityNotFoundException;
import loudsound.controllers.exceptions.InvalidArgumentsException;
import loudsound.model.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
@Scope("singleton")
public class UserRepository {
    private final Map<String, User> users;

    public UserRepository() {
        users = new HashMap<>();
    }

    public void addUser(User newUser) {
        users.put(newUser.getUsername(), newUser);
    }

    public User register(NewUserDTO newUserDTO) {
        if (users.containsKey(newUserDTO.getUsername()))
            throw new InvalidArgumentsException("User with given username already exists.");
        User user = new User(newUserDTO);
        addUser(user);
        return user;
    }

    public User getUser(String username) {
        User existingUser = users.get(username);
        if (existingUser == null) {
            throw new EntityNotFoundException("User with given username: " + username + " does not exist.");
        }
        return existingUser;
    }

    public Collection<User> getAllUsers() {
        return Collections.unmodifiableCollection(users.values());
    }
}
