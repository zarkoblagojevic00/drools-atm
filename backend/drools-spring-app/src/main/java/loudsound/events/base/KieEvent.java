package loudsound.events.base;

import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import java.io.Serializable;
import java.util.Date;

@Role(Role.Type.EVENT)
@Timestamp("occurred")
public abstract class KieEvent implements Serializable {

    private static final long serialVersionUID = 1L;
    private final String causerId;
    private final Date occurred;

    public KieEvent(String causerId) {
        this.causerId = causerId;
        this.occurred = new Date();
    }

    public String getCauserId() {
        return causerId;
    }

    public Date getOccurred() {
        return occurred;
    }
}
