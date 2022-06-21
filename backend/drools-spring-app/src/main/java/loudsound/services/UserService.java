package loudsound.services;

import loudsound.controllers.dtos.NewUserDTO;
import loudsound.model.User;
import loudsound.repositories.UserRepository;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@DependsOn({"stateInitializerService"})
public class UserService {

    private final UserRepository userRepo;
    private final KieSession kieSession;

    @Autowired
    public UserService(UserRepository userRepo, KieSession kieSession) {
        this.userRepo = userRepo;
        this.kieSession = kieSession;
    }

    public User registerUser(NewUserDTO newUserDTO) {
        User newUser = userRepo.register(newUserDTO);
        kieSession.insert(newUser);
        kieSession.fireAllRules();
        return newUser;
    }

    public Collection<User> getUsers() {
        return userRepo.getAllUsers();
    }
}
