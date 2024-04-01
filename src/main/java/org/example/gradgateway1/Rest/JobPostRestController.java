package org.example.gradgateway1.Rest;


import jakarta.persistence.Access;
import org.example.gradgateway1.DTO.JobPostDTO;
import org.example.gradgateway1.Services.JobPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/jobpost")
public class JobPostRestController {

    private final JobPostService jobPostService;

    @Autowired
    public JobPostRestController(@RequestBody JobPostService jobPostService) {
        this.jobPostService = jobPostService;
    }

    @PostMapping("/add")
    public String addJobPost(@RequestBody JobPostDTO jobPostDTO) {
        jobPostService.addJob(jobPostDTO);
        return "Job post added";
    }

    @PutMapping("/update/{id}")
    public String updateJobPost(@RequestBody JobPostDTO jobPostDTO,@PathVariable long id) {
        jobPostService.updateJob(jobPostDTO, id);
        return "Job post updated";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteJobPost(@PathVariable long id) {
        jobPostService.deleteJob(id);
        return "Job post deleted";
    }

    @GetMapping("/jobpost/{id}")
    public String getJobPost(@PathVariable long id) {
        return jobPostService.getJob(id).toString();
    }

    @GetMapping("/jobpost/title/{title}")
    public String getJobPostByTitle(@PathVariable String title) {
        return jobPostService.getJobByTitle(title).toString();
    }

    @GetMapping("/jobpost/location/{location}")
    public String getJobPostByLocation(@PathVariable String location) {
        return jobPostService.getJobByLocation(location).toString();
    }
    @GetMapping("/jobpost/type/{type}")
    public String getJobPostByType(@PathVariable String type) {
        return jobPostService.getJobByType(type).toString();
    }

    @GetMapping("/jobpost/experience/{experience}")
    public String getJobPostByExperience(@PathVariable int experience) {
        return jobPostService.getJobByExperience(experience).toString();
    }

    @GetMapping("/jobpost/salary/{salary}")
    public String getJobPostBySalary(@PathVariable int salary) {
        return jobPostService.getJobBySalary(salary).toString();
    }

    @GetMapping("/jobpost/visa/{visa}")
    public String getJobPostByVisaSponsorship(@PathVariable Boolean visa) {
        return jobPostService.getJobByVisaSponsorship(visa).toString();
    }

    @GetMapping("/jobpost/posteddate/{date}")
    public String getJobPostByPostedDate(@PathVariable Date date) {
        return jobPostService.getJobByPostedDate(date).toString();
    }

    @GetMapping("/jobpost/company/{company}")
    public String getJobPostByCompany(@PathVariable String company) {
        return jobPostService.getJobByCompany(company).toString();
    }





}
