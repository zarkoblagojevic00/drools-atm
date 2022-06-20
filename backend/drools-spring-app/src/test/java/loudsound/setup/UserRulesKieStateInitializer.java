package loudsound.setup;

import loudsound.controllers.dtos.NewSongDTO;
import loudsound.controllers.dtos.NewUserDTO;
import loudsound.model.User;
import loudsound.stateinitializer.DiscographyStateNode;
import loudsound.stateinitializer.KieStateInitializer;
import loudsound.stateinitializer.SongStateNode;

import java.util.Arrays;

public class UserRulesKieStateInitializer {
    public static KieStateInitializer getAnonymousUserToBeDeclaredRisingStarBySongActivityInitializer() {
        return new KieStateInitializer(
                Arrays.asList(
                        new DiscographyStateNode(
                                new NewUserDTO(TestUtil.OTHER_ARTIST, User.Genre.ROCK),
                                Arrays.asList(
                                        new SongStateNode(
                                                new NewSongDTO(TestUtil.OTHER_ARTIST, "TEST_SONG1", 220),
                                                3, 15, 0
                                        ),
                                        new SongStateNode(
                                                new NewSongDTO(TestUtil.OTHER_ARTIST, "TEST_SONG2", 220),
                                                2, 10, 0
                                        ),
                                        new SongStateNode(
                                                new NewSongDTO(TestUtil.OTHER_ARTIST, "TEST_SONG3", 220),
                                                2, 10, 2
                                        )
                                )
                        ),
                        new DiscographyStateNode(
                                new NewUserDTO(TestUtil.ARTIST, User.Genre.ROCK),
                                Arrays.asList(
                                        new SongStateNode(
                                                new NewSongDTO(TestUtil.ARTIST, "SONG1", 220),
                                                3, 2, 0
                                        ),
                                        new SongStateNode(
                                                new NewSongDTO(TestUtil.ARTIST, "SONG2", 220),
                                                2, 2, 0
                                        ),
                                        new SongStateNode(
                                                new NewSongDTO(TestUtil.ARTIST, TestUtil.SONG, 220),
                                                2, 1, 2
                                        )
                                )
                        )));
    }

    public static KieStateInitializer getAnonymousUserToBeDeclaredRisingStarByTopChartActivityInitializer() {
        return new KieStateInitializer(
                Arrays.asList(
                        new DiscographyStateNode(
                                new NewUserDTO(TestUtil.OTHER_ARTIST, User.Genre.ROCK),
                                Arrays.asList(
                                        new SongStateNode(
                                                new NewSongDTO(TestUtil.OTHER_ARTIST, "TEST_SONG1", 220),
                                                3, 15, 0
                                        ),
                                        new SongStateNode(
                                                new NewSongDTO(TestUtil.OTHER_ARTIST, "TEST_SONG2", 220),
                                                2, 10, 0
                                        ),
                                        new SongStateNode(
                                                new NewSongDTO(TestUtil.OTHER_ARTIST, "TEST_SONG3", 220),
                                                2, 10, 2
                                        )
                                )
                        ),
                        new DiscographyStateNode(
                                new NewUserDTO(TestUtil.ARTIST, User.Genre.ROCK),
                                Arrays.asList(
                                        new SongStateNode(
                                                new NewSongDTO(TestUtil.ARTIST, "SONG1", 220),
                                                1, 2, 0
                                        ),
                                        new SongStateNode(
                                                new NewSongDTO(TestUtil.ARTIST, "SONG2", 220),
                                                2, 12, 0
                                        ),
                                        new SongStateNode(
                                                new NewSongDTO(TestUtil.ARTIST, TestUtil.SONG, 220),
                                                1, 10, 2
                                        )
                                )
                        )));
    }

    public static KieStateInitializer getRisingStarUserInitializer() {
        return new KieStateInitializer(
                Arrays.asList(
                        new DiscographyStateNode(
                                new NewUserDTO(TestUtil.ARTIST, User.Genre.ROCK),
                                Arrays.asList(
                                        new SongStateNode(
                                                new NewSongDTO(TestUtil.ARTIST, "TEST_SONG1", 220),
                                                5, 5, 0
                                        ),
                                        new SongStateNode(
                                                new NewSongDTO(TestUtil.ARTIST, "TEST_SONG2", 220),
                                                2, 3, 0
                                        ),
                                        new SongStateNode(
                                                new NewSongDTO(TestUtil.ARTIST, TestUtil.SONG, 220),
                                                3, 2, 2
                                        )
                                )
                        )));
    }


    public static KieStateInitializer getRisingStarUserToBeDeclaredLegendInitializer() {
        return new KieStateInitializer(
                Arrays.asList(
                        new DiscographyStateNode(
                                new NewUserDTO(TestUtil.ARTIST, User.Genre.ROCK),
                                Arrays.asList(
                                        new SongStateNode(
                                                new NewSongDTO(TestUtil.ARTIST, "TEST_SONG1", 220),
                                                5  , 4, 0
                                        ),
                                        new SongStateNode(
                                                new NewSongDTO(TestUtil.ARTIST, "TEST_SONG2", 220),
                                                12, 2, 0
                                        ),
                                        new SongStateNode(
                                                new NewSongDTO(TestUtil.ARTIST, TestUtil.SONG, 220),
                                                3, 4, 2
                                        )
                                )
                        )));
    }
}
