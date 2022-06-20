package loudsound.stateinitializer;

import loudsound.controllers.dtos.NewUserDTO;
import loudsound.model.User;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiscographyStateNode extends KieStateNode implements KieStateInitializable {
    private static final Logger logger = LoggerFactory.getLogger(DiscographyStateNode.class);
    private final User user;
    private final List<SongStateNode> songNodes;
    private Map<String, FactHandle> songHandles;

    public DiscographyStateNode(NewUserDTO userDTO, List<SongStateNode> songNodes) {
        this.user = new User(userDTO);
        this.songNodes = songNodes;
        this.songHandles = new HashMap<>();
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

    @Override
    public String getStateNodeId() {
        return user.getUsername();
    }

    public FactHandle getSongFactHandle(String songId) {
        return songHandles.get(songId);
    }

    private void initializeUser(KieSession session) {
        factHandle = session.insert(user);
        session.fireAllRules();
    }

    private void initializeSongs(KieSession session) {
        for (SongStateNode songNode: songNodes) {
            songNode.initializeState(session);
            songHandles.put(songNode.getStateNodeId(), songNode.getFactHandle());
        }
    }


}
