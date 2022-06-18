package loudsound.stateinitializer;

import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class KieStateInitializer implements KieStateInitializable {
    private static final Logger logger = LoggerFactory.getLogger(KieStateInitializer.class);
    private final List<DiscographyStateNode> nodes;

    public KieStateInitializer(List<DiscographyStateNode> nodes) {
        this.nodes = nodes;
    }

    public List<DiscographyStateNode> getNodes() {
        return nodes;
    }

    @Override
    public void initializeState(KieSession session) {
        logger.info("State initialization started.");
        for (DiscographyStateNode node: nodes) {
            node.initializeState(session);
        }
        logger.info("State initialization ended.");
    }
}
