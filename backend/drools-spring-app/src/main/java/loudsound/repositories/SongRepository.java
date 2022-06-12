package loudsound.repositories;

import loudsound.controllers.dtos.NewSongDTO;
import loudsound.controllers.exceptions.EntityNotFoundException;
import loudsound.controllers.exceptions.InvalidArgumentsException;
import loudsound.model.Song;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
@Scope("singleton")
public class SongRepository {
    private final Map<String, Song> songs;

    public SongRepository() {
        songs = new HashMap<>();
    }

    public Song release(NewSongDTO newSong) {
        if (checkIfSongExists(newSong))
            throw new InvalidArgumentsException("Song with the same title and artist already exists.");
        Song song = new Song(newSong);
        songs.put(song.getId(), song);
        return song;
    }

    public Song getSong(String songId) {
        Song existingSong = songs.get(songId);
        if (existingSong == null) {
            throw new EntityNotFoundException("Song with the given id: " + songId + " does not exist.");
        }
        return existingSong;
    }

    public Collection<Song> getAllSongs() {
        return Collections.unmodifiableCollection(songs.values());
    }

    private boolean checkIfSongExists(NewSongDTO newSong) {
        return songs.values().stream().anyMatch(newSong::isSameAs);
    }

    public void update(Song existingSong) {
        songs.put(existingSong.getId(), existingSong);
    }
}
