package loudsound.controllers.dtos;

import loudsound.model.User;

public class NewUserDTO {
    private final String username;
    private final User.Genre genre;

    public NewUserDTO(String username, User.Genre genre) {
        this.username = username;
        this.genre = genre;
    }

    public String getUsername() {
        return username;
    }

    public User.Genre getGenre() {
        return genre;
    }
}
