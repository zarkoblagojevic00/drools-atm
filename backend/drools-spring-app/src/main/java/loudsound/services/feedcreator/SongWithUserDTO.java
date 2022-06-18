package loudsound.services.feedcreator;

import loudsound.model.Song;
import loudsound.model.User;

public class SongWithUserDTO {
    private Song song;
    private User artist;

    public SongWithUserDTO() {
    }

    public SongWithUserDTO(Song song, User artist) {
        this.song = song;
        this.artist = artist;
    }

    public Song getSong() {
        return song;
    }

    public User getArtist() {
        return artist;
    }
}
