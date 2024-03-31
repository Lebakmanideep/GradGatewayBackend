package org.example.gradgateway1.Services;

import org.example.gradgateway1.DTO.UserDataDTO;
import org.example.gradgateway1.Entity.UserData;

import java.util.List;

public interface UserDataService {
    void addUserData(UserDataDTO userDataDTO);

    void deleteUserData(Long id);

    void updateUserData(UserDataDTO userDataDTO, long id);

    List<UserData> getUserData();

}
