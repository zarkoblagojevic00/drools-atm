package loudsound.model;

import loudsound.controllers.dtos.NewUserDTO;
import org.kie.api.definition.type.Modifies;
import org.kie.api.definition.type.PropertyReactive;

@PropertyReactive
public class User {
    public enum Title {
        ANONYMOUS, RISING_STAR, LEGEND
    }

    public enum Genre {
        ROCK, METAL, JAZZ, BLUES, RAP, COUNTRY
    }


    private final String username;
    private Title title;
    private boolean isPopular;
    private final Genre genre;

    public User(NewUserDTO newUser) {
        this.username = newUser.getUsername();
        this.genre = newUser.getGenre();
        this.title = Title.ANONYMOUS;
        this.isPopular = false;
    }

    public String getUsername() {
        return username;
    }

    public Genre getGenre() {
        return genre;
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
                ", genre=" + genre +
                '}';
    }
}
