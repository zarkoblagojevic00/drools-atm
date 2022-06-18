package loudsound.services;

import loudsound.controllers.dtos.NewSongDTO;
import loudsound.events.song.SongLikedEvent;
import loudsound.events.song.SongListeningEndedEvent;
import loudsound.events.song.SongListeningStartedEvent;
import loudsound.model.Song;
import loudsound.repositories.SongRepository;
import loudsound.repositories.UserRepository;
import loudsound.services.feedcreator.FeedCreator;
import loudsound.services.feedcreator.UserFeedDTO;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@DependsOn({"stateInitializerService"})
public class SongService {

    private final SongRepository songRepo;
    private final UserRepository userRepo;
    private final KieSession kieSession;
    private final FeedCreator feedCreator;

    @Autowired
    public SongService(SongRepository songRepo,
                       UserRepository userRepo,
                       FeedCreator feedCreator,
                       KieSession kieSession) {
        this.songRepo = songRepo;
        this.userRepo = userRepo;
        this.feedCreator = feedCreator;
        this.kieSession = kieSession;
        this.kieSession.setGlobal("feedCreator", this.feedCreator);
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
        kieSession.insert(new SongLikedEvent(username, songId));
        kieSession.fireAllRules();
        return songRepo.getSong(songId);
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

    public UserFeedDTO getSongsForUser(String username) {
        return feedCreator.createFeedForUser(username, getAllSongs(), userRepo::getUser);
    }
}