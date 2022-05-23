package loudsound.events.user;

import loudsound.events.base.KieEvent;

public class UserReleasedSongEvent extends KieEvent {
    private final String songId;

    public UserReleasedSongEvent(String causerId, String songId) {
        super(causerId);
        this.songId = songId;
    }

    public String getSongId() {
        return songId;
    }
}
