package loudsound.services.feedcreator;

import java.util.Collection;

public class UserFeedDTO {
    private Collection<SongWithUserDTO> recommended;
    private Collection<SongWithUserDTO> others;

    public UserFeedDTO() {
    }

    public UserFeedDTO(Collection<SongWithUserDTO> recommendations, Collection<SongWithUserDTO> others) {
        this.recommended = recommendations;
        this.others = others;
    }

    public Collection<SongWithUserDTO> getRecommended() {
        return recommended;
    }

    public Collection<SongWithUserDTO> getOthers() {
        return others;
    }
}
