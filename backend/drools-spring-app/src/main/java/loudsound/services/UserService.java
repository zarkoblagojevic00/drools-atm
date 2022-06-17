package loudsound.services;

import loudsound.controllers.dtos.NewUserDTO;
import loudsound.model.User;
import loudsound.repositories.UserRepository;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final KieSession kieSession;

    @Autowired
    public UserService(UserRepository userRepo, KieSession kieSession) {
        this.userRepo = userRepo;
        this.kieSession = kieSession;
        setupUsersState();
    }

    public User registerUser(NewUserDTO newUserDTO) {
        User newUser = userRepo.register(newUserDTO);
        kieSession.insert(newUser);
        kieSession.fireAllRules();
        return newUser;
    }

    private void setupUsersState() {
        Logger logger = LoggerFactory.getLogger(UserService.class);
        logger.debug("User initialization started...");
        for (NewUserDTO dto: getInitUsers()) {
            User newUser = registerUser(dto);
            logger.debug("User initialized: {}", newUser);
        }
        logger.debug("User initialization finished.");
    }

    private List<NewUserDTO> getInitUsers() {
        return Arrays.asList(
                new NewUserDTO("test", User.Genre.ROCK),
                new NewUserDTO("Filler", User.Genre.ROCK),
                new NewUserDTO("Arctic Monkeys", User.Genre.ROCK),
                new NewUserDTO("The Strokes", User.Genre.ROCK),
                new NewUserDTO("John Coltrane", User.Genre.JAZZ),
                new NewUserDTO("Eminem", User.Genre.RAP),
                new NewUserDTO("BB King", User.Genre.BLUES),
                new NewUserDTO("John Denver", User.Genre.COUNTRY),
                new NewUserDTO("Dolly Parton", User.Genre.COUNTRY),
                new NewUserDTO("System Of A Down", User.Genre.METAL)
        );
    }
}
