package org.example.gradgateway1.Services;

import org.example.gradgateway1.DAO.UserDataRepository;
import org.example.gradgateway1.DTO.UserDataDTO;
import org.example.gradgateway1.Entity.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDataServiceImpl implements UserDataService{

    private final UserDataRepository userDataRepository;

    @Autowired
    public UserDataServiceImpl(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }
    @Override
    public void addUserData(UserDataDTO userDataDTO) {

        UserData userData = UserData.builder()
                .id(0L)
                .mobile(userDataDTO.getMobile())
                .location(userDataDTO.getLocation())
                .experience(userDataDTO.getExperience())
                .dateOfBirth(userDataDTO.getDob())
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
