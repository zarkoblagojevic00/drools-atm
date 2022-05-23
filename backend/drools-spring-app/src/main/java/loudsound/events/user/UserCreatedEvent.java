package loudsound.events.user;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import java.io.Serializable;
import java.util.Date;

@Role(Role.Type.EVENT)
@Timestamp("occurred")
@Expires("20h")
public class UserCreatedEvent implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String causerId;
    private final Date occurred;

    public UserCreatedEvent(String causerId) {
        super();
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

