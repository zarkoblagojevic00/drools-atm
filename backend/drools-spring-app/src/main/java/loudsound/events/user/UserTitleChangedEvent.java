package loudsound.events.user;

import loudsound.model.User;
import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import java.io.Serializable;
import java.util.Date;

@Role(Role.Type.EVENT)
@Timestamp("occurred")
@Expires("20h")
public class UserTitleChangedEvent implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String causerId;
    private final Date occurred;
    private final User.Title title;

    public UserTitleChangedEvent(String causerId, User.Title title) {
        super();
        this.causerId = causerId;
        this.title = title;
        this.occurred = new Date();
    }

    public UserTitleChangedEvent(String causerId, Date occurred, User.Title title) {
        this.causerId = causerId;
        this.occurred = occurred;
        this.title = title;
    }

    public String getCauserId() {
        return causerId;
    }

    public Date getOccurred() {
        return occurred;
    }

    public User.Title getTitle() {
        return title;
    }
}

