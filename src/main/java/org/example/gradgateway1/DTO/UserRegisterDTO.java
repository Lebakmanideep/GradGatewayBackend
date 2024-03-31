package org.example.gradgateway1.DTO;

import lombok.Data;

@Data
public class UserRegisterDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
}
