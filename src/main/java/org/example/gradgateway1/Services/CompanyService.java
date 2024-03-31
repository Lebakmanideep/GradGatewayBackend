package org.example.gradgateway1.Services;

import org.example.gradgateway1.DTO.CompanyDTO;
import org.example.gradgateway1.Entity.Company;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CompanyService {
    Company getCompanyById(long id);

    void addCompany(CompanyDTO companyDTO);

    void updateCompany(CompanyDTO companyDTO, long id);

    void deleteCompany(long id);

    List<Company> getAllCompanies();

    List<Company> getCompaniesByLocation(String location);

    List<Company> getCompaniesBySize(int size);

    String uploadImage(MultipartFile file, long id) throws IOException;

    byte[] getImage(long id) throws IOException;
}
