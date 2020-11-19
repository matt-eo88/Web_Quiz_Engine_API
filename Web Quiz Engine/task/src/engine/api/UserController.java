package engine.api;

import engine.model.User;
import engine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("api/register")
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void createUser(@Valid @RequestBody User user) {
        userService.insertUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUserById(@PathVariable("id") int id) {
        userService.deleteUserById(id);
    }
}
