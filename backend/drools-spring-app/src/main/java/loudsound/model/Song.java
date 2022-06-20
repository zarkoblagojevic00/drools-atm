package loudsound.model;

import loudsound.controllers.dtos.NewSongDTO;
import org.kie.api.definition.type.Modifies;
import org.kie.api.definition.type.PropertyReactive;

import java.util.Date;

@PropertyReactive
public class Song {
    public enum Status {
        POPULAR, OK, BORING
    }

    private final String id;
    private final String artist;
    private final String title;
    private final Date created;
    private final long length;

    private long likesNumber;
    private long timesListenedNumber;
    private long timesSkippedNumber;
    private Status status;

    public Song(NewSongDTO newSong) {
        this.artist = newSong.getArtist();
        this.title = newSong.getTitle();
        this.length = newSong.getDuration();

        this.created = new Date();
        this.status = Status.OK;
        this.id = setupId();
    }

    private String setupId() {
        String[] nameSplit = artist.split(" ");
        String[] titleSplit = title.split(" ");
        StringBuilder builder = new StringBuilder();
        for (String namePart: nameSplit) {
            builder.append(namePart);
            builder.append("-");
        }
        for (String titlePart: titleSplit) {
            builder.append(titlePart);
            builder.append("-");
        }

        return builder.toString().replaceAll(".$", "");
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

    public long getLength() {
        return length;
    }

    public long getLikesNumber() {
        return likesNumber;
    }

    @Modifies({"likesNumber"})
    public void like() {
        this.likesNumber++;
    }

    public long getTimesListenedNumber() {
        return timesListenedNumber;
    }

    @Modifies({"timesListenedNumber"})
    public void listen() {
        this.timesListenedNumber++;
    }

    public long getTimesSkippedNumber() {
        return timesSkippedNumber;
    }

    @Modifies({"timesSkippedNumber"})
    public void skip() {
        this.timesSkippedNumber++;
    }

    public Status getStatus() {
        return status;
    }

    @Modifies({"status"})
    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id='" + id + '\'' +
                ", artist='" + artist + '\'' +
                ", title='" + title + '\'' +
                ", created=" + created +
                ", length=" + length +
                ", likesNumber=" + likesNumber +
                ", timesListenedNumber=" + timesListenedNumber +
                ", timesSkippedNumber=" + timesSkippedNumber +
                ", status=" + status +
                '}';
    }
}
