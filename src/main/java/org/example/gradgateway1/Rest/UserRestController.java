package org.example.gradgateway1.Rest;

import org.example.gradgateway1.DTO.UserRegisterDTO;
import org.example.gradgateway1.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public String addUser(@RequestBody UserRegisterDTO userRegisterDTO) {
        userService.addUser(userRegisterDTO);
        return "User added";
    }

    @GetMapping("/users")
    public String getUsers() {
        return userService.getUsers().toString();
    }
    @GetMapping("/user/{id}")
    public String getUser(@PathVariable Long id) {
        return userService.getUserById(id).toString();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "User deleted";
    }
}
