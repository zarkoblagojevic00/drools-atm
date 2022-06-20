package loudsound.setup;

import loudsound.controllers.dtos.NewSongDTO;
import loudsound.controllers.dtos.NewUserDTO;
import loudsound.model.User;
import loudsound.stateinitializer.DiscographyStateNode;
import loudsound.stateinitializer.KieStateInitializer;
import loudsound.stateinitializer.SongStateNode;

import java.util.Arrays;

public class SongRulesKieStateInitializer {


    public static KieStateInitializer getOkSongToBeDeclaredBoringInitializer() {
        return new KieStateInitializer(
                Arrays.asList(
                new DiscographyStateNode(
                        new NewUserDTO(TestUtil.ARTIST, User.Genre.ROCK),
                        Arrays.asList(
                                new SongStateNode(
                                        new NewSongDTO(TestUtil.ARTIST, TestUtil.SONG, 220),
                                        0, 2, 4
                                )
                        )
                )));
    }

    public static KieStateInitializer getBoringSongToBeRedeclaredOkInitializer() {
        return new KieStateInitializer(
                Arrays.asList(
                        new DiscographyStateNode(
                                new NewUserDTO(TestUtil.ARTIST, User.Genre.ROCK),
                                Arrays.asList(
                                        new SongStateNode(
                                                new NewSongDTO(TestUtil.ARTIST, TestUtil.SONG, 220),
                                                0, 2, 5
                                        )
                                )
                        )));
    }

    public static KieStateInitializer getSongsWithOneSongAboutToReachTopN() {
        return new KieStateInitializer(
                Arrays.asList(
                        new DiscographyStateNode(
                                new NewUserDTO(TestUtil.ARTIST, User.Genre.ROCK),
                                Arrays.asList(
                                        new SongStateNode(
                                                new NewSongDTO(TestUtil.ARTIST, "TOPN-1", 220),
                                                0, 4, 0
                                        ),
                                        new SongStateNode(
                                                new NewSongDTO(TestUtil.ARTIST, "TOPN-2", 220),
                                                0, 5, 0
                                        ),
                                        new SongStateNode(
                                                new NewSongDTO(TestUtil.ARTIST, "TOPN-3", 220),
                                                0, 7, 0
                                        ),
                                        new SongStateNode(
                                                new NewSongDTO(TestUtil.ARTIST, TestUtil.SONG, 220),
                                                0, 4, 0
                                        )
                                )
                        )));
    }

    public static KieStateInitializer getOkSongToBeDeclaredPopularInitializer() {
        return new KieStateInitializer(
                Arrays.asList(
                        new DiscographyStateNode(
                                new NewUserDTO(TestUtil.ARTIST, User.Genre.ROCK),
                                Arrays.asList(
                                        new SongStateNode(
                                                new NewSongDTO(TestUtil.ARTIST, TestUtil.SONG, 220),
                                                10, 4, 2
                                        )
                                )
                        )));
    }

    public static KieStateInitializer getPopularSongInitializer() {
        return new KieStateInitializer(
                Arrays.asList(
                        new DiscographyStateNode(
                                new NewUserDTO(TestUtil.ARTIST, User.Genre.ROCK),
                                Arrays.asList(
                                        new SongStateNode(
                                                new NewSongDTO(TestUtil.ARTIST, TestUtil.SONG, 220),
                                                11, 4, 2
                                        )
                                )
                        )));
    }
}
