package loudsound.model;

import org.kie.api.definition.type.Modifies;
import org.kie.api.definition.type.PropertyReactive;

@PropertyReactive
public class User {
    public enum Title {
        ANONYMOUS, RISING_STAR, LEGEND
    }
    private final String username;
    private Title title;
    private boolean isPopular;

    public User(String username) {
        this.username = username;
        this.title = Title.ANONYMOUS;
        this.isPopular = false;
    }

    public String getUsername() {
        return username;
    }

    public Title getTitle() {
        return title;
    }

    @Modifies({"title"})
    public void setTitle(Title title) {
        this.title = title;
    }

    public boolean isPopular() {
        return isPopular;
    }

    @Modifies("popular")
    public void setPopular(boolean popular) {
        isPopular = popular;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", title=" + title +
                ", isPopular=" + isPopular +
                '}';
    }
}
