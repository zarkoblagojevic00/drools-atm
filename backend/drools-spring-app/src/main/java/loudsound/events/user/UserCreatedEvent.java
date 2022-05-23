package loudsound.events.user;

import loudsound.events.base.KieEvent;
import org.kie.api.definition.type.Expires;

@Expires("500h")
public class UserCreatedEvent extends KieEvent {
    public UserCreatedEvent(String causerId) {
        super(causerId);
    }
}
