package loudsound.setup;

import loudsound.events.song.SongLikedEvent;
import loudsound.events.song.SongListeningEndedEvent;
import loudsound.events.song.SongListeningStartedEvent;
import org.drools.core.time.SessionPseudoClock;
import org.kie.api.runtime.KieSession;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TestUtil {
    public static final String ARTIST = "Test Artist";
    public static final String OTHER_ARTIST = "Other Artist";
    public static final String SONG = "Test Title";
    public static final String SONG_ID = "Test-Artist-Test-Title";

    public static void likeTestSong(KieSession kieSession) {
        SessionPseudoClock kieClock = kieSession.getSessionClock();
        kieSession.insert(new SongLikedEvent("T", new Date(kieClock.getCurrentTime()), TestUtil.SONG_ID));
        kieSession.fireAllRules();
    }

    public static void insertListeningEventsWithTimeApart(KieSession kieSession,int timeInSeconds) {
        SessionPseudoClock kieClock = kieSession.getSessionClock();
        kieSession.insert(new SongListeningStartedEvent("T", new Date(kieClock.getCurrentTime()), TestUtil.SONG_ID));
        kieClock.advanceTime(timeInSeconds, TimeUnit.SECONDS);
        kieSession.insert(new SongListeningEndedEvent("T", new Date(kieClock.getCurrentTime()), TestUtil.SONG_ID));
        kieSession.fireAllRules();
    }
}
