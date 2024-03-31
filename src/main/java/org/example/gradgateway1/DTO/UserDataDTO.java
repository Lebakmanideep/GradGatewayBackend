package org.example.gradgateway1.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class UserDataDTO {

    private String mobile;
    private String location;
    private Date dob;
    private int experience;

}
