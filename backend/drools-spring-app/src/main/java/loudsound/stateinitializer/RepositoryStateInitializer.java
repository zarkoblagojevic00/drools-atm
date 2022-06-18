package loudsound.stateinitializer;

import loudsound.repositories.SongRepository;
import loudsound.repositories.UserRepository;

import java.util.List;

public class RepositoryStateInitializer{
    private final UserRepository userRepo;
    private final SongRepository songRepo;

    public RepositoryStateInitializer(UserRepository userRepo, SongRepository songRepo) {
        this.userRepo = userRepo;
        this.songRepo = songRepo;
    }

    public void initializeState(List<DiscographyStateNode> nodes) {
        for (DiscographyStateNode node: nodes) {
            userRepo.addUser(node.getUser());
            for (SongStateNode songNode: node.getSongNodes()) {
                songRepo.addSong(songNode.getSong());
            }
        }
    }
}
