package org.example.gradgateway1.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "company")
public class Company {

    // define the feilds


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String companyName;


    private String location;

    private String url;

    private String description;


    private int size;


    private String logo;









}
