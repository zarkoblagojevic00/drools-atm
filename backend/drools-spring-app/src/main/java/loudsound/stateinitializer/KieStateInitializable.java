package loudsound.stateinitializer;

import org.kie.api.runtime.KieSession;

public interface KieStateInitializable {
    public void initializeState(KieSession session);
}
