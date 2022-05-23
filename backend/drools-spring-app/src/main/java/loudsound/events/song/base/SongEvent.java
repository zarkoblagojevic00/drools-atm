package loudsound.events.song.base;

import loudsound.events.base.KieEvent;

public abstract class SongEvent extends KieEvent {
    private final String songId;

    public SongEvent(String causerId, String songId) {
        super(causerId);
        this.songId = songId;
    }

    public String getSongId() {
        return songId;
    }
}
