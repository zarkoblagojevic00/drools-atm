package loudsound.events.song;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import java.io.Serializable;
import java.util.Date;

@Role(Role.Type.EVENT)
@Timestamp("occurred")
@Expires("100h")
public class SongSkippedEvent implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String causerId;
    private final Date occurred;
    private final String songId;

    public SongSkippedEvent(String causerId, String songId) {
        super();
        this.causerId = causerId;
        this.occurred = new Date();
        this.songId = songId;
    }

    public SongSkippedEvent(String causerId, Date occurred, String songId) {
        this.causerId = causerId;
        this.occurred = occurred;
        this.songId = songId;
    }

    public String getCauserId() {
        return causerId;
    }

    public Date getOccurred() {
        return occurred;
    }

    public String getSongId() {
        return songId;
    }

    @Override
    public String toString() {
        return "SongSkippedEvent{" +
                "causerId='" + causerId + '\'' +
                ", occurred=" + occurred +
                ", songId='" + songId + '\'' +
                '}';
    }
}

