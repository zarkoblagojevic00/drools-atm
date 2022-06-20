package loudsound.stateinitializer;

import loudsound.controllers.dtos.NewSongDTO;
import loudsound.events.song.SongLikedEvent;
import loudsound.events.song.SongListenedEvent;
import loudsound.events.song.SongSkippedEvent;
import loudsound.model.Song;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class SongStateNode extends KieStateNode implements KieStateInitializable  {
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
        initializeSong(session);
        initializeLikes(session);
        initializeListens(session);
        initializeSkips(session);
        logger.info("Song initialization ended: {}", song);
    }

    @Override
    public String getStateNodeId() {
        return song.getId();
    }

    private void initializeSong(KieSession session) {
        factHandle = session.insert(song);
        session.fireAllRules();
    }

    private void initializeLikes(KieSession session) {
        for (int i = 0; i < likesNumber; i++) {
            likeSong(session);
        }
    }

    private void likeSong(KieSession session) {
        session.insert(new SongLikedEvent(CAUSER, getCurrentDate(session), song.getId()));
        session.fireAllRules();
    }

    private void initializeListens(KieSession session) {
        for (int i = 0; i < timesListenedNumber; i++) {
            listenSong(session);
        }
    }

    private void listenSong(KieSession session) {
        song.listen();
        session.update(factHandle, song);
        session.insert(new SongListenedEvent(CAUSER, getCurrentDate(session), song.getId()));
        session.fireAllRules();
    }

    private void initializeSkips(KieSession session) {
        for (int i = 0; i < timesSkippedNumber; i++) {
            skipSong(session);
        }
    }

    private void skipSong(KieSession session) {
        song.skip();
        session.update(factHandle, song);
        session.insert(new SongSkippedEvent(CAUSER, getCurrentDate(session), song.getId()));
        session.fireAllRules();
    }

    private Date getCurrentDate(KieSession session) {
        return new Date(session.getSessionClock().getCurrentTime());
    }
}
