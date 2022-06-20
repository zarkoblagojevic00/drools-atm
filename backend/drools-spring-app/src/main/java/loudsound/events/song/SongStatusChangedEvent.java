package loudsound.events.song;

import loudsound.model.Song;
import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import java.io.Serializable;
import java.util.Date;

@Role(Role.Type.EVENT)
@Timestamp("occurred")
@Expires("100h")
public class SongStatusChangedEvent implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Date occurred;
    private final String songId;
    private final Song.Status status;

    public SongStatusChangedEvent(String songId, Song.Status status) {
        super();
        this.occurred = new Date();
        this.songId = songId;
        this.status = status;
    }

    public SongStatusChangedEvent(Date occurred, String songId, Song.Status status) {
        this.occurred = occurred;
        this.songId = songId;
        this.status = status;
    }

    public Date getOccurred() {
        return occurred;
    }

    public String getSongId() {
        return songId;
    }

    public Song.Status getStatus() {
        return status;
    }

}


