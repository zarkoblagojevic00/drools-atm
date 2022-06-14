package loudsound.services;

import loudsound.controllers.dtos.NewSongDTO;
import loudsound.events.song.*;
import loudsound.model.Song;
import loudsound.repositories.SongRepository;
import loudsound.repositories.UserRepository;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
@DependsOn("userService")
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
        this.kieSession.insert(this.feedCreator);
        setupSongsState();
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

    private void setupSongsState() {
        Logger logger = LoggerFactory.getLogger(SongService.class);
        logger.debug("Song initialization started...");
        List<NewSongDTO> songs = getInitSongs();
        List<Integer> likes = getInitLikes();
        List<Integer> listens = getInitListens();
        List<Integer> skips = getInitSkips();

        for (int i = 0; i < songs.size(); i++) {
            Song newSong = releaseSong(songs.get(i));
            addLikes(newSong, likes.get(i));
            addListens(newSong, listens.get(i));
            addSkips(newSong, skips.get(i));
            logger.debug("Song initialized: {}", newSong);
        }
        logger.debug("Song initialization finished.");
    }

    private void addLikes(Song newSong, Integer likesNum) {
        for (int i = 0; i < likesNum; i++) {
            likeSong(newSong.getId(), "Hedonist");
        }
    }

    private void addListens(Song newSong, Integer listensNum) {
        for (int i = 0; i < listensNum; i++) {
            newSong.listen();
            kieSession.insert(new SongListenedEvent("Hedonist", newSong.getId()));
            kieSession.fireAllRules();
        }
    }

    private void addSkips(Song newSong, Integer skipsNum) {
        for (int i = 0; i < skipsNum; i++) {
            newSong.skip();
            kieSession.insert(new SongSkippedEvent("Hedonist", newSong.getId()));
            kieSession.fireAllRules();
        }
    }


    private List<NewSongDTO> getInitSongs() {
        return Arrays.asList(
                new NewSongDTO("Arctic Monkeys", "505", 310),
                new NewSongDTO("Arctic Monkeys", "Red Lights", 225),
                new NewSongDTO("Arctic Monkeys", "Riot Van", 200),
                new NewSongDTO("Arctic Monkeys", "Cornerstone", 212),
                new NewSongDTO("The Strokes", "Adults are talking", 225),
                new NewSongDTO("The Strokes", "Bad Decisions", 200),
                new NewSongDTO("The Strokes", "At the Door", 212),
                new NewSongDTO("The Strokes", "Heart in a Cage", 222),
                new NewSongDTO("John Coltrane", "Jazz Standard 1", 212),
                new NewSongDTO("John Coltrane", "Jazz Standard 2", 220),
                new NewSongDTO("John Coltrane", "Jazz Standard 3", 243),
                new NewSongDTO("John Coltrane", "Jazz Standard 4", 225),
                new NewSongDTO("John Coltrane", "Jazz Standard 4", 225),
                new NewSongDTO("Eminem", "Till I collapse", 225),
                new NewSongDTO("Eminem", "Mocking bird", 250),
                new NewSongDTO("Eminem", "Venom", 240),
                new NewSongDTO("Eminem", "Berserk", 212),
                new NewSongDTO("BB King", "Thrill is gone", 221),
                new NewSongDTO("BB King", "Lucile", 300),
                new NewSongDTO("John Denver", "Country roads", 280),
                new NewSongDTO("Dolly Parton", "Jolene", 180),
                new NewSongDTO("System Of A Down", "Radio-Video", 180),
                new NewSongDTO("System Of A Down", "Hypnotize", 220),
                new NewSongDTO("System Of A Down", "Toxicity", 210),
                new NewSongDTO("System Of A Down", "Sugar", 280)
        );
    }

    private List<Integer> getInitLikes() {
        return Arrays.asList(
                30, 5, 5, 6,        // Arctic Monkeys
                5, 4, 3, 6,         // The Strokes
                12, 10, 10, 10, 1,  // John Coltrane
                2, 2, 5, 0,         // Eminem
                2, 2,               // BB King
                3,                  // John Denver
                12,                 // Dolly Parton
                8, 5, 4, 3          // System Of A Down
        );
    }

    private List<Integer> getInitListens() {
        return Arrays.asList(
                4, 5, 12, 5,        // Arctic Monkeys
                5, 3, 4, 1,          // The Strokes
                8, 5, 3, 3, 2,      // John Coltrane
                1, 1, 5, 0,         // Eminem
                1, 2,               // BB King
                4,                  // John Denver
                8,                  // Dolly Parton
                18, 3, 2, 3         // System Of A Down
        );
    }

    private List<Integer> getInitSkips() {
        return Arrays.asList(
                0, 0, 0, 2,         // Arctic Monkeys
                0, 2, 1, 0,         // The Strokes
                0, 2, 0, 2, 0,      // John Coltrane
                1, 1, 1, 1,         // Eminem
                1, 2,               // BB King
                5,                  // John Denver
                4,                  // Dolly Parton
                0, 0, 1, 0          // System Of A Down
        );
    }

}
