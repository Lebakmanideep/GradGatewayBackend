package org.example.gradgateway1.DAO;

import org.example.gradgateway1.Entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    List<Company> findAllByCompanyNameLikeIgnoreCase(String name);

    List<Company> findAllByLocationLikeIgnoreCase(String location);

    List<Company> findAllBySizeLessThanEqual(int size);



}
