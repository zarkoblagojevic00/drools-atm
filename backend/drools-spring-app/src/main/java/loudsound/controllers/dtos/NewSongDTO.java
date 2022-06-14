package loudsound.controllers.dtos;

import loudsound.model.Song;

public class NewSongDTO {
    private final String artist;
    private final String title;
    private final long duration;

    public NewSongDTO(String artist, String title, long duration) {
        this.artist = artist;
        this.title = title;
        this.duration = duration;
    }

    public String getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }

    public long getDuration() {
        return duration;
    }

    public boolean isSameAs(Song song) {
        return artist.equals(song.getArtist()) && title.equals(song.getArtist());
    }
}
