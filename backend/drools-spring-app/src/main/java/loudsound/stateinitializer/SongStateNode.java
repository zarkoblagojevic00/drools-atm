package loudsound.stateinitializer;

import loudsound.controllers.dtos.NewSongDTO;
import loudsound.events.song.SongLikedEvent;
import loudsound.events.song.SongListenedEvent;
import loudsound.events.song.SongSkippedEvent;
import loudsound.model.Song;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SongStateNode implements KieStateInitializable {
    private static final Logger logger = LoggerFactory.getLogger(SongStateNode.class);
    private static final String CAUSER = "__CAUSER__";
    private final Song song;
    private final int likesNumber;
    private final int timesListenedNumber;
    private final int timesSkippedNumber;

    public SongStateNode(NewSongDTO songDTO, int likesNumber, int timesListenedNumber, int timesSkippedNumber) {
        this.song = new Song(songDTO);
        this.likesNumber = likesNumber;
        this.timesListenedNumber = timesListenedNumber;
        this.timesSkippedNumber = timesSkippedNumber;
    }

    public Song getSong() {
        return song;
    }

    @Override
    public void initializeState(KieSession session) {
        logger.info("Song initialization started: {} - {}", song.getArtist(), song.getTitle());
        FactHandle songHandle = initializeSong(session);
        initializeLikes(session);
        initializeListens(session, songHandle);
        initializeSkips(session, songHandle);
        logger.info("Song initialization ended: {}", song);
    }

    private FactHandle initializeSong(KieSession session) {
        FactHandle songHandle = session.insert(song);
        session.fireAllRules();
        return songHandle;
    }

    private void initializeLikes(KieSession session) {
        for (int i = 0; i < likesNumber; i++) {
            likeSong(session);
        }
    }

    private void likeSong(KieSession session) {
        session.insert(new SongLikedEvent(CAUSER, song.getId()));
        session.fireAllRules();
    }

    private void initializeListens(KieSession session, FactHandle songHandle) {
        for (int i = 0; i < timesListenedNumber; i++) {
            listenSong(session, songHandle);
        }
    }

    private void listenSong(KieSession session, FactHandle songHandle) {
        song.listen();
        session.update(songHandle, song);
        session.insert(new SongListenedEvent(CAUSER, song.getId()));
        session.fireAllRules();
    }

    private void initializeSkips(KieSession session, FactHandle songHandle) {
        for (int i = 0; i < timesSkippedNumber; i++) {
            skipSong(session, songHandle);
        }
    }

    private void skipSong(KieSession session, FactHandle songHandle) {
        song.skip();
        session.update(songHandle, song);
        session.insert(new SongSkippedEvent(CAUSER, song.getId()));
        session.fireAllRules();
    }
}
