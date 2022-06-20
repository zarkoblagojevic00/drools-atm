package loudsound.stateinitializer;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KieStateInitializer implements KieStateInitializable {
    private static final Logger logger = LoggerFactory.getLogger(KieStateInitializer.class);
    private final List<DiscographyStateNode> nodes;
    private Map<String, DiscographyStateNode> discNodes;

    public KieStateInitializer(List<DiscographyStateNode> nodes) {
        this.nodes = nodes;
        this.discNodes = new HashMap<>();
    }

    public List<DiscographyStateNode> getNodes() {
        return nodes;
    }

    public FactHandle getUserFactHandle(String userId) {
        return discNodes.get(userId).getFactHandle();
    }

    public FactHandle getSongFactHandle(String userId, String songId) {
        return discNodes.get(userId).getSongFactHandle(songId);
    }

    @Override
    public void initializeState(KieSession session) {
        logger.info("State initialization started.");
        for (DiscographyStateNode node: nodes) {
            node.initializeState(session);
            discNodes.put(node.getStateNodeId(), node);
        }
        logger.info("State initialization ended.");
    }
}
