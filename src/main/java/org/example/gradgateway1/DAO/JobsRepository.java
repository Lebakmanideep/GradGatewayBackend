package org.example.gradgateway1.DAO;

import org.example.gradgateway1.Entity.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface JobsRepository extends JpaRepository<JobPost, Long> {

    List<JobPost> findAllByTitleLikeIgnoreCase (String title);

    List<JobPost> findAllByLocationLikeIgnoreCase(String location);

    List<JobPost> findAllByTypeLikeIgnoreCase(String type);

    List<JobPost> findAllByExperienceLessThanEqual(int experience);

    List<JobPost> findAllBySalaryLessThanEqual(int salary);

    List<JobPost> findAllByVisaSponsorship(Boolean visa);

    List<JobPost> findAllByPostedDateAfter(Date date);

    @Query("SELECT j FROM JobPost j WHERE j.company.id = :companyId")
    List<JobPost> findAllByCompanyId(long companyId);


}
