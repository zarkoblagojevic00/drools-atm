package loudsound.events.user;

import loudsound.events.base.KieEvent;

public class UserBecamePopularEvent extends KieEvent {
    public UserBecamePopularEvent(String causerId) {
        super(causerId);
    }
}
