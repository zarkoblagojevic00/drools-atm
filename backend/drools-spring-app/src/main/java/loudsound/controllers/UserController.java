package loudsound.controllers;

import loudsound.controllers.dtos.NewUserDTO;
import loudsound.model.User;
import loudsound.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<Collection<User>> registerUser() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping()
    public ResponseEntity<User> registerUser(@RequestBody NewUserDTO dto) {
        return ResponseEntity.ok(userService.registerUser(dto));
    }
}
