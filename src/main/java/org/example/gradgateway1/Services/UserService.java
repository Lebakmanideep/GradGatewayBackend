package org.example.gradgateway1.Services;

import org.example.gradgateway1.DTO.UserRegisterDTO;
import org.example.gradgateway1.Entity.User;

import java.util.List;

public interface UserService {

    void addUser(UserRegisterDTO userRegisterDTO);

    List<User> getUsers();

    User getUserById(Long id);

    void deleteUser(Long id);

}
