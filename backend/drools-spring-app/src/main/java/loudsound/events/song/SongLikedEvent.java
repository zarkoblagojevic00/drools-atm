package loudsound.events.song;

import loudsound.events.song.base.SongEvent;

public class SongLikedEvent extends SongEvent{
    public SongLikedEvent(String causerId, String songId) {
        super(causerId, songId);
    }
}
