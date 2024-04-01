package org.example.gradgateway1.Services;

import org.example.gradgateway1.DAO.UserDataRepository;
import org.example.gradgateway1.DAO.UserRepository;
import org.example.gradgateway1.DTO.UserDataDTO;
import org.example.gradgateway1.Entity.User;
import org.example.gradgateway1.Entity.UserData;
import org.example.gradgateway1.Util.AuthenticationDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDataServiceImpl implements UserDataService{

    private final UserDataRepository userDataRepository;

    private final AuthenticationDetails authenticationDetails;

    @Autowired
    public UserDataServiceImpl(UserDataRepository userDataRepository, AuthenticationDetails authenticationDetails) {
        this.userDataRepository = userDataRepository;
        this.authenticationDetails = authenticationDetails;
    }
    @Override
    public void addUserData(UserDataDTO userDataDTO) {
        Optional<User> user = authenticationDetails.getUser();
        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        UserData userData = UserData.builder()
                .id(0L)
                .mobile(userDataDTO.getMobile())
                .location(userDataDTO.getLocation())
                .experience(userDataDTO.getExperience())
                .dateOfBirth(userDataDTO.getDob())
                .user(user.get())
                .build();
        userDataRepository.save(userData);

    }

    @Override
    public void deleteUserData(Long id) {
        userDataRepository.deleteById(id);


    }

    @Override
    public void updateUserData(UserDataDTO userDataDTO, long id) {

        UserData userData = userDataRepository.findById(id).orElse(null);
        if (userData != null) {
            userData.setMobile(userDataDTO.getMobile());
            userData.setLocation(userDataDTO.getLocation());
            userData.setExperience(userDataDTO.getExperience());
            userData.setDateOfBirth(userDataDTO.getDob());
            userDataRepository.save(userData);
        }

    }

    @Override
    public List<UserData> getUserData() {
        return userDataRepository.findAll();

    }
}
