package org.example.gradgateway1.Rest;

import org.example.gradgateway1.DTO.UserDataDTO;
import org.example.gradgateway1.Services.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/userdata")
public class UserDataRestController {

    private final UserDataService userDataService;

    @Autowired
    public UserDataRestController(UserDataService userDataService) {
        this.userDataService = userDataService;
    }

    @PostMapping("/add")
    public String addUserData(@RequestBody UserDataDTO userDataDTO) {
        userDataService.addUserData(userDataDTO);
        return "User data added";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUserData(@PathVariable Long id) {
        userDataService.deleteUserData(id);
        return "User data deleted";
    }

    @PutMapping("/update/{id}")
    public String updateUserData(@PathVariable Long id, @RequestBody UserDataDTO userDataDTO) {
        userDataService.updateUserData(userDataDTO, id);
        return "User data updated";
    }

    @GetMapping("/userdata")
    public String getUserData() {
        return userDataService.getUserData().toString();
    }
}
