package loudsound.services;

import loudsound.controllers.dtos.NewSongDTO;
import loudsound.events.song.SongLikedEvent;
import loudsound.events.song.SongListeningEndedEvent;
import loudsound.events.song.SongListeningStartedEvent;
import loudsound.model.Song;
import loudsound.repositories.SongRepository;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SongService {

    private final SongRepository songRepo;
    private final KieSession kieSession;

    @Autowired
    public SongService(SongRepository songRepo, KieSession kieSession) {
        this.songRepo = songRepo;
        this.kieSession = kieSession;
    }

    public Song releaseSong(NewSongDTO newSongDTO) {
        Song newSong = songRepo.release(newSongDTO);
        kieSession.insert(newSong);
        kieSession.fireAllRules();
        return newSong;
    }

    public Collection<Song> getAllSongs() {
        return songRepo.getAllSongs();
    }

    public Song likeSong(String songId, String username) {
        Song existingSong = songRepo.getSong(songId);
        existingSong.like();
        songRepo.update(existingSong);

        kieSession.insert(new SongLikedEvent(username, songId));
        kieSession.fireAllRules();

        return existingSong;
    }

    public void startSong(String songId, String username) {
        kieSession.insert(new SongListeningStartedEvent(username, songId));
        kieSession.fireAllRules();
    }

    public Song endSong(String songId, String username) {
        kieSession.insert(new SongListeningEndedEvent(username, songId));
        kieSession.fireAllRules();
        return songRepo.getSong(songId);
    }
}
