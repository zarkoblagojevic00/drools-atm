package loudsound.controllers.dtos;

import loudsound.model.Song;

import java.util.Date;

public class NewSongDTO {
    private final String artist;
    private final String title;
    private final long duration;
    private final Song.Genre genre;


    public NewSongDTO(String artist, String title, Date created, long duration, Song.Genre genre) {
        this.artist = artist;
        this.title = title;
        this.duration = duration;
        this.genre = genre;
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

    public Song.Genre getGenre() {
        return genre;
    }

    public boolean isSameAs(Song song) {
        return artist.equals(song.getArtist()) && title.equals(song.getArtist());
    }
}
