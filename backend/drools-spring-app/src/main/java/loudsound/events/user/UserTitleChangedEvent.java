package loudsound.events.user;

import loudsound.events.base.KieEvent;
import loudsound.model.User;

public class UserTitleChangedEvent extends KieEvent {
    private final User.Title newTitle;

    public UserTitleChangedEvent(String causerId, User.Title newTitle) {
        super(causerId);
        this.newTitle = newTitle;
    }

    public User.Title getNewTitle() {
        return newTitle;
    }
}
