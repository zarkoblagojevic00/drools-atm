package loudsound.stateinitializer;

import org.kie.api.runtime.rule.FactHandle;

public abstract class KieStateNode {
    protected FactHandle factHandle;

    public FactHandle getFactHandle() {
        return factHandle;
    }

    public abstract String getStateNodeId();

}
