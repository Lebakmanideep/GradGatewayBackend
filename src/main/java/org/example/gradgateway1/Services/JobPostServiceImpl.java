package org.example.gradgateway1.Services;


import org.example.gradgateway1.DAO.CompanyRepository;
import org.example.gradgateway1.DAO.JobsRepository;
import org.example.gradgateway1.DAO.UserRepository;
import org.example.gradgateway1.DTO.JobPostDTO;
import org.example.gradgateway1.Entity.Company;
import org.example.gradgateway1.Entity.JobPost;
import org.example.gradgateway1.Entity.User;
import org.example.gradgateway1.Util.AuthenticationDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class JobPostServiceImpl implements JobPostService{
    private final JobsRepository jobsRepository;

    private final CompanyRepository companyRepository;

    private final AuthenticationDetails authenticationDetails;
    @Autowired
    public JobPostServiceImpl(JobsRepository jobsRepository, CompanyRepository companyRepository, AuthenticationDetails authenticationDetails) {
        this.jobsRepository = jobsRepository;
        this.companyRepository = companyRepository;
        this.authenticationDetails = authenticationDetails;
    }
    @Override
    public void addJob(JobPostDTO jobPostDTO) {
        Company company = companyRepository.findById(jobPostDTO.getCompanyId()).orElse(null);
        Optional<User> user = authenticationDetails.getUser();
        if (company == null || user.isEmpty()) {
            throw new RuntimeException("Company or User not found");
        }

        JobPost jobPost = JobPost.builder()
                .id(0L)
                .description(jobPostDTO.getDescription())
                .title(jobPostDTO.getTitle())
                .location(jobPostDTO.getLocation())
                .type(jobPostDTO.getType())
                .experience(jobPostDTO.getExperience())
                .salary(jobPostDTO.getSalary())
                .postedDate(new Date())
                .company(company)
                .user(user.get())
                .visaSponsorship(jobPostDTO.getVisaSponsorship())
                .build();
        jobsRepository.save(jobPost);

    }

    @Override
    public void updateJob(JobPostDTO jobPostDTO, long id) {
        Optional<JobPost> jobPost = jobsRepository.findById(id);
        if (jobPost.isPresent()) {
            jobPost.get().setTitle(jobPostDTO.getTitle());
            jobPost.get().setDescription(jobPostDTO.getDescription());
            jobPost.get().setLocation(jobPostDTO.getLocation());
            jobPost.get().setType(jobPostDTO.getType());
            jobPost.get().setExperience(jobPostDTO.getExperience());
            jobPost.get().setSalary(jobPostDTO.getSalary());
            jobPost.get().setVisaSponsorship(jobPostDTO.getVisaSponsorship());
            jobsRepository.save(jobPost.get());
        }



    }

    @Override
    public void deleteJob(Long id) {
        jobsRepository.deleteById(id);

    }

    @Override
    public JobPost getJob(Long id) {
        Optional<JobPost> jobPost = jobsRepository.findById(id);
        return jobPost.orElse(null);
    }

    @Override
    public List<JobPost> getJobByTitle(String title) {
        return jobsRepository.findAllByTitleLikeIgnoreCase(title);
    }

    @Override
    public List<JobPost> getJobByLocation(String location) {
        return jobsRepository.findAllByLocationLikeIgnoreCase(location);
    }

    @Override
    public List<JobPost> getJobByType(String type) {
        return jobsRepository.findAllByTypeLikeIgnoreCase(type);
    }

    @Override
    public List<JobPost> getJobByExperience(int experience) {
        return jobsRepository.findAllByExperienceLessThanEqual(experience);
    }

    @Override
    public List<JobPost> getJobBySalary(int salary) {
        return jobsRepository.findAllBySalaryLessThanEqual(salary);
    }

    @Override
    public List<JobPost> getJobByVisaSponsorship(Boolean visa) {
        return jobsRepository.findAllByVisaSponsorship(visa);
    }

    @Override
    public List<JobPost> getJobByPostedDate(Date date) {
        return jobsRepository.findAllByPostedDateAfter(date);
    }

    @Override
    public List<JobPost> getJobByCompany(String company) {
        return jobsRepository.findAllByCompanyId(Long.parseLong(company));
    }

}
