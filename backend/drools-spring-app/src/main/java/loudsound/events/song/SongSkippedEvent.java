package loudsound.events.song;

import loudsound.events.song.base.SongEvent;

public class SongSkippedEvent extends SongEvent {
    public SongSkippedEvent(String causerId, String songId) {
        super(causerId, songId);
    }
}
