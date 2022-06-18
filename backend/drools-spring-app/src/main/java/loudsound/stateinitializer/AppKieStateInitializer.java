package loudsound.stateinitializer;

import loudsound.controllers.dtos.NewSongDTO;
import loudsound.controllers.dtos.NewUserDTO;
import loudsound.model.User;

import java.util.Arrays;
import java.util.List;

public class AppKieStateInitializer extends KieStateInitializer {

    public AppKieStateInitializer() {
        super(getInitKieStateNodes());
    }

    private static List<DiscographyStateNode> getInitKieStateNodes() {
        return Arrays.asList(
                new DiscographyStateNode(
                        new NewUserDTO("HedonisT", User.Genre.ROCK),
                        Arrays.asList(
                                new SongStateNode(
                                        new NewSongDTO("HedonisT", "Testic 1", 212),
                                        5, 5, 2),
                                new SongStateNode(
                                        new NewSongDTO("HedonisT", "Testic 2", 212),
                                        3, 5, 0),
                                new SongStateNode(
                                        new NewSongDTO("HedonisT", "Testic 3", 212),
                                        5, 5, 3)
                        )
                ),
                new DiscographyStateNode(
                        new NewUserDTO("John Coltrane", User.Genre.JAZZ),
                        Arrays.asList(
                                new SongStateNode(
                                        new NewSongDTO("John Coltrane", "Jazz Standard 1", 212),
                                        12, 8, 0),
                                new SongStateNode(
                                        new NewSongDTO("John Coltrane", "Jazz Standard 2", 220),
                                        10, 5, 2),
                                new SongStateNode(
                                        new NewSongDTO("John Coltrane", "Jazz Standard 3", 243),
                                        10, 3, 0),
                                new SongStateNode(
                                        new NewSongDTO("John Coltrane", "Jazz Standard 4", 225),
                                        10, 3, 2),
                                new SongStateNode(
                                        new NewSongDTO("John Coltrane", "Jazz Standard 5", 225),
                                        1, 2, 0)

                        )
                ),
                new DiscographyStateNode(
                        new NewUserDTO("Eminem", User.Genre.RAP),
                        Arrays.asList(
                                new SongStateNode(
                                        new NewSongDTO("Eminem", "Till I collapse", 225),
                                        2, 1, 1),
                                new SongStateNode(
                                        new NewSongDTO("Eminem", "Mocking bird", 250),
                                        2, 1, 1),
                                new SongStateNode(
                                        new NewSongDTO("Eminem", "Venom", 240),
                                        5, 5, 1),
                                new SongStateNode(
                                        new NewSongDTO("Eminem", "Berserk", 212),
                                        0, 0, 1)
                        )
                ),
                new DiscographyStateNode(
                        new NewUserDTO("BB King", User.Genre.BLUES),
                        Arrays.asList(
                                new SongStateNode(
                                        new NewSongDTO("BB King", "Thrill is gone", 221),
                                        2, 1, 1),
                                new SongStateNode(
                                        new NewSongDTO("BB King", "Lucile", 300),
                                        2, 2, 2)

                        )
                ),
                new DiscographyStateNode(
                        new NewUserDTO("John Denver", User.Genre.COUNTRY),
                        Arrays.asList(
                                new SongStateNode(
                                        new NewSongDTO("John Denver", "Country roads", 280),
                                        3, 4, 5)
                        )
                ),
                new DiscographyStateNode(
                        new NewUserDTO("Dolly Parton", User.Genre.COUNTRY),
                        Arrays.asList(
                                new SongStateNode(
                                        new NewSongDTO("Dolly Parton", "Jolene", 180),
                                        12, 8, 4)
                        )
                ),
                new DiscographyStateNode(
                        new NewUserDTO("System Of A Down", User.Genre.METAL),
                        Arrays.asList(
                                new SongStateNode(
                                        new NewSongDTO("System Of A Down", "Radio-Video", 180),
                                        8, 18, 0),
                                new SongStateNode(
                                        new NewSongDTO("System Of A Down", "Hypnotize", 220),
                                        5, 3, 0),
                                new SongStateNode(
                                        new NewSongDTO("System Of A Down", "Toxicity", 210),
                                        4, 2, 1),
                                new SongStateNode(
                                        new NewSongDTO("System Of A Down", "Sugar", 280),
                                        3, 3, 0)
                        )
                ),
                new DiscographyStateNode(
                        new NewUserDTO("Arctic Monkeys", User.Genre.ROCK),
                        Arrays.asList(
                                new SongStateNode(
                                        new NewSongDTO("Arctic Monkeys", "505", 310),
                                        30, 12, 0),
                                new SongStateNode(
                                        new NewSongDTO("Arctic Monkeys", "Red Lights", 225),
                                        5, 5, 0),
                                new SongStateNode(
                                        new NewSongDTO("Arctic Monkeys", "Riot Van", 200),
                                        4, 4, 0),
                                new SongStateNode(
                                        new NewSongDTO("Arctic Monkeys", "Cornerstone", 212),
                                        6, 5, 2)
                        )
                ),

                new DiscographyStateNode(
                        new NewUserDTO("The Strokes", User.Genre.ROCK),
                        Arrays.asList(
                                new SongStateNode(
                                        new NewSongDTO("The Strokes", "Adults are talking", 225),
                                        5, 5, 0),
                                new SongStateNode(
                                        new NewSongDTO("The Strokes", "Bad Decisions", 200),
                                        4, 4, 2),
                                new SongStateNode(
                                        new NewSongDTO("The Strokes", "At the Door", 212),
                                        3, 3, 1),
                                new SongStateNode(
                                        new NewSongDTO("The Strokes", "Heart in a Cage", 222),
                                        1, 1, 0)
                        )
                )

        );
    }


}
