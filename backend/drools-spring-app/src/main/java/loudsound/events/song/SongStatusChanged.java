package loudsound.events.song;

import loudsound.events.song.base.SongEvent;
import loudsound.model.Song;

public class SongStatusChanged extends SongEvent {
    private final Song.Status newStatus;

    public SongStatusChanged(String causerId, String songId, Song.Status newStatus) {
        super(causerId, songId);
        this.newStatus = newStatus;
    }

    public Song.Status getNewStatus() {
        return newStatus;
    }
}
