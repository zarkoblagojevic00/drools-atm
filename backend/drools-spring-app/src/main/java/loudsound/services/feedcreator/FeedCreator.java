package loudsound.services.feedcreator;

import loudsound.model.Song;
import loudsound.model.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Scope("singleton")
public class FeedCreator {
    private final Map<String, Collection<Song>> recommendations;

    public FeedCreator() {
        recommendations = new HashMap<>();
    }

    public void updateRecommendations(String username, Collection<Song> newRecommendations) {
        recommendations.put(username, newRecommendations);
    }


    public UserFeedDTO createFeedForUser(String username, Collection<Song> allSongs, Function<String, User> getUser) {
        recommendations.computeIfAbsent(username, k -> new ArrayList<>());
        Collection<Song> allSongsSorted = getAllSongsSorted(allSongs);
        Collection<Song> userRecommendations = recommendations.get(username).isEmpty()
                ? allSongsSorted.stream().limit(3).collect(Collectors.toList())
                : recommendations.get(username);


        Collection<Song> others = allSongsSorted.stream()
                .filter(s -> !userRecommendations.contains(s))
                .collect(Collectors.toList());

        return new UserFeedDTO(mapSongsToDTO(userRecommendations, getUser), mapSongsToDTO(others, getUser));
    }

    private Collection<SongWithUserDTO> mapSongsToDTO(Collection<Song> songs, Function<String, User> getUser) {
        return songs.stream()
                .map(s -> new SongWithUserDTO(s, getUser.apply(s.getArtist())))
                .collect(Collectors.toList());
    }

    private Collection<Song> getAllSongsSorted(Collection<Song> allSongs) {
        return allSongs.stream()
                .sorted((a, b) -> (int) (b.getTimesListenedNumber() - a.getTimesListenedNumber()))
                .collect(Collectors.toList());
    }
}
