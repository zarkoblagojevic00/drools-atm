package loudsound.stateinitializer;

import loudsound.controllers.dtos.NewUserDTO;
import loudsound.model.User;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DiscographyStateNode implements KieStateInitializable {
    private static final Logger logger = LoggerFactory.getLogger(DiscographyStateNode.class);
    private final User user;
    private final List<SongStateNode> songNodes;

    public DiscographyStateNode(NewUserDTO userDTO, List<SongStateNode> songNodes) {
        this.user = new User(userDTO);
        this.songNodes = songNodes;
    }

    public User getUser() {
        return user;
    }

    public List<SongStateNode> getSongNodes() {
        return songNodes;
    }

    @Override
    public void initializeState(KieSession session) {
        logger.info("Discography initialization started: {}", user.getUsername());
        initializeUser(session);
        initializeSongs(session);
        logger.info("Discography initialization ended: {}", user.getUsername());
    }

    private void initializeUser(KieSession session) {
        session.insert(user);
        session.fireAllRules();
    }

    private void initializeSongs(KieSession session) {
        for (SongStateNode songNode: songNodes) {
            songNode.initializeState(session);
        }
    }


}
