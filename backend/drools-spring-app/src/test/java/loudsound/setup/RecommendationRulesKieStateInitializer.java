package loudsound.setup;

import loudsound.controllers.dtos.NewSongDTO;
import loudsound.controllers.dtos.NewUserDTO;
import loudsound.model.User;
import loudsound.stateinitializer.DiscographyStateNode;
import loudsound.stateinitializer.KieStateInitializer;
import loudsound.stateinitializer.SongStateNode;

import java.util.Arrays;

public class RecommendationRulesKieStateInitializer {
    public static User.Genre GENRE = User.Genre.ROCK;
    public static String GENRE_LEGEND = "Arctic Monkeys";
    public static String GENRE_RISING_STAR = "The Strokes";
    public static String GENRE_RISING_STAR_SONG_ID_1 = "The-Strokes-TS_TEST_SONG1";
    public static String GENRE_RISING_STAR_SONG_ID_2 = "The-Strokes-TS_TEST_SONG2";
    public static final String GENRE_LEGEND_SONG_ID_1 = "Arctic-Monkeys-AM_TEST_SONG1";


    public static KieStateInitializer getRecommendSongsForPopularArtistAndLegendInSameGenre() {
        return new KieStateInitializer(
                Arrays.asList(
                        new DiscographyStateNode(
                                new NewUserDTO(TestUtil.ARTIST, GENRE),
                                Arrays.asList(
                                        new SongStateNode(
                                                new NewSongDTO(TestUtil.ARTIST, "TEST_SONG1", 220),
                                                1, 3, 0
                                        ),
                                        new SongStateNode(
                                                new NewSongDTO(TestUtil.ARTIST, "TEST_SONG2", 220),
                                                1, 3, 0
                                        ),
                                        new SongStateNode(
                                                new NewSongDTO(TestUtil.ARTIST, "TEST_SONG3", 220),
                                                1, 3, 0
                                        )
                                )
                        ),
                        new DiscographyStateNode(
                                new NewUserDTO(GENRE_LEGEND, GENRE),
                                Arrays.asList(
                                        new SongStateNode(
                                                new NewSongDTO(GENRE_LEGEND, "AM_TEST_SONG1", 220),
                                                20, 15, 0
                                        ),
                                        new SongStateNode(
                                                new NewSongDTO(GENRE_LEGEND, "AM_TEST_SONG2", 220),
                                                2, 8, 0
                                        ),
                                        new SongStateNode(
                                                new NewSongDTO(GENRE_LEGEND, "AM_TEST_SONG3", 220),
                                                2, 10, 2
                                        )
                                )
                        ),
                        new DiscographyStateNode(
                                new NewUserDTO(GENRE_RISING_STAR, User.Genre.ROCK),
                                Arrays.asList(
                                        new SongStateNode(
                                                new NewSongDTO(GENRE_RISING_STAR, "TS_TEST_SONG1", 220),
                                                1, 4, 0
                                        ),
                                        new SongStateNode(
                                                new NewSongDTO(GENRE_RISING_STAR, "TS_TEST_SONG2", 220),
                                                2, 3, 0
                                        ),
                                        new SongStateNode(
                                                new NewSongDTO(GENRE_RISING_STAR, "TS_TEST_SONG3", 220),
                                                1, 1, 2
                                        ),
                                        new SongStateNode(
                                                new NewSongDTO(GENRE_RISING_STAR, "TS_TEST_SONG4", 220),
                                                2, 2, 2
                                        )
                                )
                        ),
                        new DiscographyStateNode(
                                new NewUserDTO("SOAD", User.Genre.METAL),
                                Arrays.asList(
                                        new SongStateNode(
                                                new NewSongDTO("SOAD", "SOAD_TEST_SONG1", 220),
                                                1, 40, 0
                                        )
                                )
                        )
                ));
    }
    public static KieStateInitializer getRecommendSongsForUserStrivingToSucceedInGenre() {
        return new KieStateInitializer(
                Arrays.asList(
                        new DiscographyStateNode(
                                new NewUserDTO(TestUtil.ARTIST, GENRE),
                                Arrays.asList(
                                        new SongStateNode(
                                                new NewSongDTO(TestUtil.ARTIST, "TEST_SONG1", 220),
                                                1, 3, 0
                                        )
                                )
                        ),
                        new DiscographyStateNode(
                                new NewUserDTO(GENRE_LEGEND, GENRE),
                                Arrays.asList(
                                        new SongStateNode(
                                                new NewSongDTO(GENRE_LEGEND, "AM_TEST_SONG1", 220),
                                                20, 15, 0
                                        ),
                                        new SongStateNode(
                                                new NewSongDTO(GENRE_LEGEND, "AM_TEST_SONG2", 220),
                                                15, 8, 0
                                        ),
                                        new SongStateNode(
                                                new NewSongDTO(GENRE_LEGEND, "AM_TEST_SONG3", 220),
                                                13, 10, 2
                                        )
                                )
                        ),
                        new DiscographyStateNode(
                                new NewUserDTO(GENRE_RISING_STAR, User.Genre.ROCK),
                                Arrays.asList(
                                        new SongStateNode(
                                                new NewSongDTO(GENRE_RISING_STAR, "TS_TEST_SONG1", 220),
                                                1, 4, 0
                                        ),
                                        new SongStateNode(
                                                new NewSongDTO(GENRE_RISING_STAR, "TS_TEST_SONG2", 220),
                                                2, 3, 0
                                        ),
                                        new SongStateNode(
                                                new NewSongDTO(GENRE_RISING_STAR, "TS_TEST_SONG3", 220),
                                                1, 1, 2
                                        ),
                                        new SongStateNode(
                                                new NewSongDTO(GENRE_RISING_STAR, "TS_TEST_SONG4", 220),
                                                2, 2, 2
                                        )
                                )
                        ),
                        new DiscographyStateNode(
                                new NewUserDTO("SOAD", User.Genre.METAL),
                                Arrays.asList(
                                        new SongStateNode(
                                                new NewSongDTO("SOAD", "SOAD_TEST_SONG1", 220),
                                                1, 40, 0
                                        )
                                )
                        )
                ));
    }

    public static KieStateInitializer getRecommendSongsForSimilarityWithOtherUser() {
        return new KieStateInitializer(
                Arrays.asList(
                        new DiscographyStateNode(
                                new NewUserDTO(TestUtil.ARTIST, GENRE),
                                Arrays.asList(
                                        new SongStateNode(
                                                new NewSongDTO(TestUtil.ARTIST, "TEST_SONG1", 220),
                                                1, 3, 0
                                        )
                                )
                        ),
                        new DiscographyStateNode(
                                new NewUserDTO(TestUtil.OTHER_ARTIST, GENRE),
                                Arrays.asList(
                                        new SongStateNode(
                                                new NewSongDTO(TestUtil.OTHER_ARTIST, "OA_TEST_SONG1", 220),
                                                1, 3, 0
                                        )
                                )
                        ),
                        new DiscographyStateNode(
                                new NewUserDTO(GENRE_LEGEND, GENRE),
                                Arrays.asList(
                                        new SongStateNode(
                                                new NewSongDTO(GENRE_LEGEND, "AM_TEST_SONG1", 220),
                                                20, 15, 0
                                        ),
                                        new SongStateNode(
                                                new NewSongDTO(GENRE_LEGEND, "AM_TEST_SONG2", 220),
                                                15, 8, 0
                                        ),
                                        new SongStateNode(
                                                new NewSongDTO(GENRE_LEGEND, "AM_TEST_SONG3", 220),
                                                13, 10, 2
                                        )
                                )
                        ),
                        new DiscographyStateNode(
                                new NewUserDTO(GENRE_RISING_STAR, User.Genre.ROCK),
                                Arrays.asList(
                                        new SongStateNode(
                                                new NewSongDTO(GENRE_RISING_STAR, "TS_TEST_SONG1", 220),
                                                1, 4, 0
                                        ),
                                        new SongStateNode(
                                                new NewSongDTO(GENRE_RISING_STAR, "TS_TEST_SONG2", 220),
                                                2, 3, 0
                                        ),
                                        new SongStateNode(
                                                new NewSongDTO(GENRE_RISING_STAR, "TS_TEST_SONG3", 220),
                                                1, 1, 2
                                        ),
                                        new SongStateNode(
                                                new NewSongDTO(GENRE_RISING_STAR, "TS_TEST_SONG4", 220),
                                                2, 2, 2
                                        )
                                )
                        ),
                        new DiscographyStateNode(
                                new NewUserDTO("SOAD", User.Genre.METAL),
                                Arrays.asList(
                                        new SongStateNode(
                                                new NewSongDTO("SOAD", "SOAD_TEST_SONG1", 220),
                                                1, 40, 0
                                        )
                                )
                        ),
                        new DiscographyStateNode(
                                new NewUserDTO("BB King", User.Genre.BLUES),
                                Arrays.asList(
                                        new SongStateNode(
                                                new NewSongDTO("BB King", "BB_TEST_SONG1", 220),
                                                1, 4, 0
                                        )
                                )
                        ),
                        new DiscographyStateNode(
                                new NewUserDTO("Dolly Parton", User.Genre.BLUES),
                                Arrays.asList(
                                        new SongStateNode(
                                                new NewSongDTO("BB King", "DP_TEST_SONG1", 220),
                                                1, 4, 0
                                        )
                                )
                        )
                ));
    }
}
