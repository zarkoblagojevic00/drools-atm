package loudsound;

import loudsound.events.song.SongLikedEvent;
import loudsound.events.song.SongListenedEvent;
import loudsound.model.Song;
import loudsound.model.User;
import loudsound.services.feedcreator.FeedCreator;
import loudsound.services.feedcreator.SongWithUserDTO;
import loudsound.services.feedcreator.UserFeedDTO;
import loudsound.setup.RecommendationRulesKieStateInitializer;
import loudsound.setup.TestKieSessionFactory;
import loudsound.setup.TestUtil;
import loudsound.stateinitializer.KieStateInitializer;
import org.drools.core.ClassObjectFilter;
import org.drools.core.time.SessionPseudoClock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RecommendationRulesTest {
    private final static Logger logger = LoggerFactory.getLogger(RecommendationRulesTest.class);
    private KieSession kieSession;
    private SessionPseudoClock kieClock;
    private FeedCreator feedCreator;

    @BeforeEach
    public void initEach() {
        feedCreator = new FeedCreator();
        kieSession = TestKieSessionFactory.getSessionWithFeedCreator(logger, feedCreator);
        kieClock = kieSession.getSessionClock();
    }

    @DisplayName("Recommend songs of an artist popular with current user and a Legend of the same genre")
    @Test
    public void recommendSongsOfPopularArtistAndLegendFromSameGenre() {
        //  arrange
        KieStateInitializer initializer = RecommendationRulesKieStateInitializer.getRecommendSongsForPopularArtistAndLegendInSameGenre();
        initializer.initializeState(kieSession);

        //  act
        for (int i= 0; i < 4; i++) {
            TestUtil.likeSong(kieSession, TestUtil.ARTIST, RecommendationRulesKieStateInitializer.GENRE_RISING_STAR_SONG_ID_1);
        }

        for (int i= 0; i < 2; i++) {
            TestUtil.listenSongFor(kieSession, TestUtil.ARTIST, RecommendationRulesKieStateInitializer.GENRE_RISING_STAR_SONG_ID_1, 10);
        }
        TestUtil.listenSongFor(kieSession, TestUtil.ARTIST, RecommendationRulesKieStateInitializer.GENRE_RISING_STAR_SONG_ID_2, 10);

        //  assert
        Collection<SongWithUserDTO> recommendedSongs = getRecommendedSongs();
        assertTwoMostPopularSongsFromArtistAreRecommended(recommendedSongs, RecommendationRulesKieStateInitializer.GENRE_RISING_STAR);
        assertNumberOfSongsNotListenedBy(recommendedSongs, TestUtil.ARTIST, RecommendationRulesKieStateInitializer.GENRE_RISING_STAR, 2);
        assertNumberOfSongsByTitleInGenre(recommendedSongs, User.Title.LEGEND, RecommendationRulesKieStateInitializer.GENRE, 2);

    }

    @DisplayName("Recommend songs for user striving to succeed in genre")
    @Test
    public void recommendSongsForUserStrivingToSucceedInGenre() {
        //  arrange
        KieStateInitializer initializer = RecommendationRulesKieStateInitializer.getRecommendSongsForUserStrivingToSucceedInGenre();
        initializer.initializeState(kieSession);

        for (int i= 0; i < 3; i++) {
            TestUtil.listenSongFor(kieSession, TestUtil.ARTIST, RecommendationRulesKieStateInitializer.GENRE_RISING_STAR_SONG_ID_1, 10);
        }

        //  assert
        Collection<SongWithUserDTO> recommendedSongs = getRecommendedSongs();
        assertNMostPopularSongOfGenreAreRecommended(recommendedSongs, RecommendationRulesKieStateInitializer.GENRE, 2);
        assertNumberOfSongsByTitleInGenre(recommendedSongs, User.Title.RISING_STAR, RecommendationRulesKieStateInitializer.GENRE, 3);
    }

    @DisplayName("Recommend songs of a genre that current user is unsure of")
    @Test
    public void recommendSongsOfAGenreThatUserIsUnsureOf() {
        //  arrange
        KieStateInitializer initializer = RecommendationRulesKieStateInitializer.getRecommendSongsForPopularArtistAndLegendInSameGenre();
        initializer.initializeState(kieSession);

        for (int i= 0; i < 6; i++) {
            TestUtil.listenSongFor(kieSession, TestUtil.ARTIST, RecommendationRulesKieStateInitializer.GENRE_RISING_STAR_SONG_ID_1, 3);
        }
        TestUtil.likeSong(kieSession, TestUtil.ARTIST, RecommendationRulesKieStateInitializer.GENRE_LEGEND_SONG_ID_1);

        //  assert
        Collection<SongWithUserDTO> recommendedSongs = getRecommendedSongs();
        assertNMostPopularSongOfGenreAreRecommended(recommendedSongs, RecommendationRulesKieStateInitializer.GENRE, 1);
        assertNMostPopularSongsOfALegendAreRecommended(recommendedSongs, RecommendationRulesKieStateInitializer.GENRE_LEGEND, 3);
        assertNumberOfSongsByTitleInGenre(recommendedSongs, User.Title.RISING_STAR, RecommendationRulesKieStateInitializer.GENRE, 2);
    }



    @DisplayName("Recommend songs by similarity with other user")
    @Test
    public void recommendSongsBySimilarityWithOtherUser() {
        //  arrange
        KieStateInitializer initializer = RecommendationRulesKieStateInitializer.getRecommendSongsForSimilarityWithOtherUser();
        initializer.initializeState(kieSession);

        for (int i = 0; i < 5; i++) {
            TestUtil.likeSong(kieSession, TestUtil.OTHER_ARTIST, RecommendationRulesKieStateInitializer.GENRE_RISING_STAR_SONG_ID_2);
        }

        TestUtil.likeSong(kieSession, TestUtil.OTHER_ARTIST, "SOAD-SOAD_TEST_SONG1");
        TestUtil.likeSong(kieSession, TestUtil.OTHER_ARTIST, "BB-King-BB_TEST_SONG1");
        TestUtil.likeSong(kieSession, TestUtil.OTHER_ARTIST, "Dolly-Parton-DP_TEST_SONG1");

        for (int i = 0; i < 3; i++) {
            TestUtil.likeSong(kieSession, TestUtil.ARTIST, RecommendationRulesKieStateInitializer.GENRE_RISING_STAR_SONG_ID_1);
        }

        //  assert
        Collection<SongWithUserDTO> recommendedSongs = getRecommendedSongs();
        assertNMostPopularSongOfGenreAreRecommended(recommendedSongs, RecommendationRulesKieStateInitializer.GENRE, 1);
        assertNMostPopularSongsOfALegendAreRecommended(recommendedSongs, RecommendationRulesKieStateInitializer.GENRE_LEGEND, 2);
        assertNMostPopularSongsThatOtherUserLikedAreRecommended(recommendedSongs, TestUtil.OTHER_ARTIST, 2);
    }

    private void assertNMostPopularSongsThatOtherUserLikedAreRecommended(Collection<SongWithUserDTO> recommendedSongs, String otherArtist, int nBest) {
        Collection<SongLikedEvent> events = (java.util.Collection<SongLikedEvent>) kieSession.getObjects(new ClassObjectFilter(SongLikedEvent.class));
        Collection<String> otherArtistLikedSongIds = events.stream()
                .filter(e -> e.getCauserId().equals(otherArtist))
                .map(SongLikedEvent::getSongId)
                .collect(Collectors.toList());
        Collection<String> nMostPopularSongsLikedByOtherArtist = TestUtil.getSongsFromSession(kieSession).stream()
                .filter(s -> otherArtistLikedSongIds.contains(s.getId()))
                .sorted((a, b) -> (int) (b.getTimesListenedNumber() - a.getTimesListenedNumber()))
                .map(Song::getId)
                .limit(nBest)
                .collect(Collectors.toList());

        assertTrue(
                recommendedSongs.stream()
                        .map(SongWithUserDTO::getSong)
                        .map(Song::getId)
                        .collect(Collectors.toList())
                        .containsAll(nMostPopularSongsLikedByOtherArtist));
    }

    private void assertNMostPopularSongOfGenreAreRecommended(Collection<SongWithUserDTO> recommendedSongs, User.Genre genre, int nBest) {
        Collection<String> artistInGenreIds = TestUtil.getUsersFromSession(kieSession).stream()
                .filter(u -> u.getGenre() == genre)
                .map(User::getUsername)
                .collect(Collectors.toList());

        Collection<String> nMostPopularSongsFromGenreIds = TestUtil.getSongsFromSession(kieSession).stream()
                .filter(s -> s.getStatus() == Song.Status.POPULAR)
                .filter(s -> artistInGenreIds.contains(s.getArtist()))
                .sorted((a, b) -> (int) (b.getTimesListenedNumber() - a.getTimesListenedNumber()))
                .map(Song::getId)
                .limit(nBest)
                .collect(Collectors.toList());
        assertTrue(
                recommendedSongs.stream()
                        .map(SongWithUserDTO::getSong)
                        .map(Song::getId)
                        .collect(Collectors.toList())
                        .containsAll(nMostPopularSongsFromGenreIds));
    }

    private void assertNMostPopularSongsOfALegendAreRecommended(Collection<SongWithUserDTO> recommendedSongs, String legend, int nBest) {
        Collection<String> threeMostPopularSongsFromGenreOfALegendIds = TestUtil.getSongsFromSession(kieSession).stream()
                .filter(s -> s.getArtist().equals(legend))
                .sorted((a, b) -> (int) (b.getTimesListenedNumber() - a.getTimesListenedNumber()))
                .map(Song::getId)
                .limit(nBest)
                .collect(Collectors.toList());
        assertTrue(
                recommendedSongs.stream()
                        .map(SongWithUserDTO::getSong)
                        .map(Song::getId)
                        .collect(Collectors.toList())
                        .containsAll(threeMostPopularSongsFromGenreOfALegendIds));
    }

    private void assertTwoMostPopularSongsFromArtistAreRecommended(Collection<SongWithUserDTO> recommendedSongs, String artist) {
        Collection<String> twoMostPopularSongsFromArtistIds = TestUtil.getSongsFromSession(kieSession).stream()
                .filter(s -> s.getArtist().equals(artist))
                .sorted((a, b) -> (int) (b.getTimesListenedNumber() - a.getTimesListenedNumber()))
                .map(Song::getId)
                .limit(2)
                .collect(Collectors.toList());
        assertTrue(
                recommendedSongs.stream()
                    .map(SongWithUserDTO::getSong)
                    .map(Song::getId)
                    .collect(Collectors.toList())
                    .containsAll(twoMostPopularSongsFromArtistIds));
    }


    private void assertNumberOfSongsNotListenedBy(Collection<SongWithUserDTO> recommendedSongs, String listener, String artist, int expected) {
        int numberOfSongsNotListenedBy = getNumberOfSongsNotListenedBy(listener, artist, recommendedSongs);
        assertEquals(expected, numberOfSongsNotListenedBy);
    }

    private int getNumberOfSongsNotListenedBy(String listener, String artist, Collection<SongWithUserDTO> recommendedSongs) {
        Collection<SongListenedEvent> events = (java.util.Collection<SongListenedEvent>) kieSession.getObjects(new ClassObjectFilter(SongListenedEvent.class));
        Collection<String> artistSongIds = TestUtil.getSongsFromSession(kieSession).stream()
                .filter(s -> s.getArtist().equals(artist))
                .map(Song::getId)
                .collect(Collectors.toList());

        Collection<String> listenerListenedToArtistSongIds = events.stream()
                .filter(e -> e.getCauserId().equals(listener))
                .map(SongListenedEvent::getSongId)
                .filter(artistSongIds::contains)
                .collect(Collectors.toList());

        return (int) recommendedSongs.stream()
                .map(s-> s.getSong().getId())
                .filter(listenerListenedToArtistSongIds::contains)
                .count();
    }

    private void assertNumberOfSongsByTitleInGenre(Collection<SongWithUserDTO> recommendedSongs, User.Title title, User.Genre genre, int expected) {
        int numberOfSongsByLegendInGenre = getNumberOfSongsByLegendInGenre(recommendedSongs, title, genre);
        assertEquals(expected, numberOfSongsByLegendInGenre);
    }

    private int getNumberOfSongsByLegendInGenre(Collection<SongWithUserDTO> recommendedSongs, User.Title title, User.Genre genre) {
        return (int) recommendedSongs.stream()
                .map(SongWithUserDTO::getArtist)
                .filter(u -> u.getGenre() == genre)
                .filter(u -> u.getTitle() == title)
                .count();
    }


    private Collection<SongWithUserDTO> getRecommendedSongs() {
         UserFeedDTO feed = feedCreator.createFeedForUser(
                 TestUtil.ARTIST,
                 TestUtil.getSongsFromSession(kieSession),
                 this::getUserFromSession);
         return feed.getRecommended();
    }

    private User getUserFromSession(String username) {
        return TestUtil.getUsersFromSession(kieSession).stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst().get();

    }

}
