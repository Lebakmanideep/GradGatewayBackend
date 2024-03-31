package org.example.gradgateway1.DTO;

import lombok.Data;

@Data
public class JobPostDTO {

    private String title;
    private String description;
    private String location;
    private String type;
    private int salary;
    private Boolean visaSponsorship;
    private int experience;
    private long companyId;

}
