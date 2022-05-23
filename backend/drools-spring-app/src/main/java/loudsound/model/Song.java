package loudsound.model;

import java.util.Date;

public class Song {
    public enum Status {
        POPULAR, OK, BORING
    }

    public enum Genre {
        ROCK, METAL, JAZZ, BLUES, RAP, FOLK
    }

    private final String id;
    private final String artist;
    private final String title;
    private final Date created;
    private final long duration;
    private long likesNumber;
    private long timesListenedNumber;
    private long timesSkippedNumber;
    private Status status;
    private final Genre genre;

    public Song(String artist, String title, long duration, Genre genre) {
        this.artist = artist;
        this.title = title;
        this.created = new Date();
        this.duration = duration;
        this.status = Status.OK;
        this.genre = genre;
        this.id = createId();
    }

    private String createId() {
        return String.format("%s::%s::%s", this.artist, this.title, this.created.getTime());
    }

    public String getId() {
        return id;
    }

    public String getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }

    public Date getCreated() {
        return created;
    }

    public long getDuration() {
        return duration;
    }

    public long getLikesNumber() {
        return likesNumber;
    }

    public void like() {
        this.likesNumber++;
    }

    public long getTimesListenedNumber() {
        return timesListenedNumber;
    }

    public void listen() {
        this.timesListenedNumber++;
    }

    public long getTimesSkippedNumber() {
        return timesSkippedNumber;
    }

    public void skip() {
        this.timesSkippedNumber++;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Genre getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id='" + id + '\'' +
                ", artist='" + artist + '\'' +
                ", title='" + title + '\'' +
                ", created=" + created +
                ", duration=" + duration +
                ", likesNumber=" + likesNumber +
                ", timesListenedNumber=" + timesListenedNumber +
                ", timesSkippedNumber=" + timesSkippedNumber +
                ", status=" + status +
                ", genre=" + genre +
                '}';
    }
}
