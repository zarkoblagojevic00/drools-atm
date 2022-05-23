package loudsound.services;

import loudsound.model.User;
import loudsound.repositories.UserRepository;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final KieSession kieSession;

    @Autowired
    public UserService(UserRepository userRepo, KieSession kieSession) {
        this.userRepo = userRepo;
        this.kieSession = kieSession;
    }

    public User registerUser(String username) {
        User newUser = userRepo.register(username);
        kieSession.insert(newUser);
        kieSession.fireAllRules();
        return newUser;
    }
}
