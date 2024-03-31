package org.example.gradgateway1.Services;

import org.example.gradgateway1.DAO.UserRepository;
import org.example.gradgateway1.DTO.UserRegisterDTO;
import org.example.gradgateway1.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(UserRegisterDTO userRegisterDTO) {
        User user = User.builder()
                .id(0L)
                .firstName(userRegisterDTO.getFirstName())
                .lastName(userRegisterDTO.getLastName())
                .email(userRegisterDTO.getEmail())
                .password(userRegisterDTO.getPassword())
                .role(userRegisterDTO.getRole())
                .build();
        userRepository.save(user);


    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);

    }
}
