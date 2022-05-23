package loudsound.events.song;

import loudsound.events.song.base.SongEvent;

public class SongListeningEndedEvent extends SongEvent {
    public SongListeningEndedEvent(String causerId, String songId) {
        super(causerId, songId);
    }
}
