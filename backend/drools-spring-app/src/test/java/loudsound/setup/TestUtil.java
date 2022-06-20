package loudsound.setup;

import loudsound.events.song.SongLikedEvent;
import loudsound.events.song.SongListeningEndedEvent;
import loudsound.events.song.SongListeningStartedEvent;
import loudsound.model.Song;
import loudsound.model.User;
import org.drools.core.ClassObjectFilter;
import org.drools.core.time.SessionPseudoClock;
import org.kie.api.runtime.KieSession;

import java.util.Collection;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TestUtil {
    public static final String ARTIST = "Test Artist";
    public static final String OTHER_ARTIST = "Other Artist";
    public static final String SONG = "Test Title";
    public static final String SONG_ID = "Test-Artist-Test-Title";

    public static void likeTestSong(KieSession kieSession) {
        likeSong(kieSession, "T", SONG_ID);
    }

    public static void likeSong(KieSession kieSession, String causerId, String songId) {
        SessionPseudoClock kieClock = kieSession.getSessionClock();
        kieSession.insert(new SongLikedEvent(causerId, new Date(kieClock.getCurrentTime()), songId));
        kieSession.fireAllRules();
    }

    public static void listenTestSongFor(KieSession kieSession, int timeInSeconds) {
        listenSongFor(kieSession, "T", SONG_ID, timeInSeconds);
    }

    public static void listenSongFor(KieSession kieSession, String causerId, String songId, int timeInSeconds) {
        SessionPseudoClock kieClock = kieSession.getSessionClock();
        kieSession.insert(new SongListeningStartedEvent(causerId, new Date(kieClock.getCurrentTime()), songId));
        kieClock.advanceTime(timeInSeconds, TimeUnit.SECONDS);
        kieSession.insert(new SongListeningEndedEvent(causerId, new Date(kieClock.getCurrentTime()), songId));
        kieSession.fireAllRules();
    }

    public static Collection<Song> getSongsFromSession(KieSession kieSession) {
        return (java.util.Collection<Song>) kieSession.getObjects(new ClassObjectFilter(Song.class));
    }

    public static Collection<User> getUsersFromSession(KieSession kieSession) {
        return (java.util.Collection<User>) kieSession.getObjects(new ClassObjectFilter(User.class));
    }
}
