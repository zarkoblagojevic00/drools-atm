package loudsound.events.song;

import loudsound.events.song.base.SongEvent;

public class SongListeningStartedEvent extends SongEvent {
    public SongListeningStartedEvent(String causerId, String songId) {
        super(causerId, songId);
    }
}
