package org.example.gradgateway1.Services;

import org.example.gradgateway1.DTO.JobPostDTO;
import org.example.gradgateway1.Entity.JobPost;

import java.util.Date;
import java.util.List;

public interface JobPostService {
    void addJob(JobPostDTO jobPostDTO);
    void updateJob(JobPostDTO jobPostDTO, long id);
    void deleteJob(Long id);
    JobPost getJob(Long id);

    List<JobPost> getJobByTitle(String title);

    List<JobPost> getJobByLocation(String location);

    List<JobPost> getJobByType(String type);

    List<JobPost> getJobByExperience(int experience);

    List<JobPost> getJobBySalary(int salary);

    List<JobPost> getJobByVisaSponsorship(Boolean visa);

    List<JobPost> getJobByPostedDate(Date date);

    List<JobPost> getJobByCompany(String company);





}
