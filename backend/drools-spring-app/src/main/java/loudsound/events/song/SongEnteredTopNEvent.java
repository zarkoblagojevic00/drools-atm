package loudsound.events.song;

import org.kie.api.definition.type.*;

import java.io.Serializable;
import java.util.Date;

@Role(Role.Type.EVENT)
@Timestamp("occurred")
@Expires("100h")
@PropertyReactive
public class SongEnteredTopNEvent implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Date occurred;
    private final String songId;
    private boolean revoked;

    public SongEnteredTopNEvent(String songId) {
        super();
        this.occurred = new Date();
        this.songId = songId;
        this.revoked = false;
    }

    public SongEnteredTopNEvent(Date occurred, String songId) {
        this.occurred = occurred;
        this.songId = songId;
    }

    public Date getOccurred() {
        return occurred;
    }

    public String getSongId() {
        return songId;
    }

    public boolean isRevoked() {
        return revoked;
    }

    @Modifies({"revoked"})
    public void revoke() {
        this.revoked = true;
    }

    @Override
    public String toString() {
        return "SongEnteredTopNEvent{" +
                "revoked=" + revoked +
                ", occurred=" + occurred +
                ", songId='" + songId + '\'' +
                '}';
    }
}

