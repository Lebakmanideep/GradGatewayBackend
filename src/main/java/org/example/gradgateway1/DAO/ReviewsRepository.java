package org.example.gradgateway1.DAO;

import org.example.gradgateway1.Entity.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewsRepository extends JpaRepository<Reviews, Long> {

    @Query("SELECT r FROM Reviews r WHERE r.company.id = :companyId")
    List<Reviews> findAllByCompanyId(Long companyId);

    @Query("SELECT r FROM Reviews r WHERE r.company.id = :companyId order by r.rating desc")
    List<Reviews> findAllByCompanyIdOrderByBest(Long companyId);

    @Query("SELECT r FROM Reviews r WHERE r.company.id = :companyId order by r.rating asc")
    List<Reviews> findAllByCompanyIdOrderByWorst(Long companyId);
}
