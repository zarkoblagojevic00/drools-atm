package loudsound.events.song;

import loudsound.events.song.base.SongEvent;

public class SongListenedEvent extends SongEvent {
    public SongListenedEvent(String causerId, String songId) {
        super(causerId, songId);
    }
}
