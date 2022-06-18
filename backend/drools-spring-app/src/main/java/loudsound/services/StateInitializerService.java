package loudsound.services;

import loudsound.repositories.SongRepository;
import loudsound.repositories.UserRepository;
import loudsound.stateinitializer.AppKieStateInitializer;
import loudsound.stateinitializer.RepositoryStateInitializer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StateInitializerService {

    @Autowired
    public StateInitializerService(KieSession session, UserRepository userRepo, SongRepository songRepo) {
        AppKieStateInitializer kieInit = new AppKieStateInitializer();
        kieInit.initializeState(session);
        new RepositoryStateInitializer(userRepo, songRepo).initializeState(kieInit.getNodes());
    }


}
