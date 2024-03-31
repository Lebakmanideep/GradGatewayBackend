package org.example.gradgateway1.Services;

import org.example.gradgateway1.DAO.CompanyRepository;
import org.example.gradgateway1.DTO.CompanyDTO;
import org.example.gradgateway1.Entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService{

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Company getCompanyById(long id) {
        Optional<Company> company = companyRepository.findById(id);
        return company.orElse(null);
    }

    @Override
    public void addCompany(CompanyDTO companyDTO) {
        Company company = Company.builder()
                .id(0L)
                .logo(null)
                .companyName(companyDTO.getName())
                .url(companyDTO.getUrl())
                .location(companyDTO.getLocation())
                .description(companyDTO.getDescription())
                .size(companyDTO.getSize())
                .build();
        companyRepository.save(company);

    }

    @Override
    public void updateCompany(CompanyDTO companyDTO, long id) {
        Optional<Company> company = companyRepository.findById(id);
        if (company.isPresent()) {
            company.get().setCompanyName(companyDTO.getName());
            company.get().setLocation(companyDTO.getLocation());
            company.get().setUrl(companyDTO.getUrl());
            company.get().setDescription(companyDTO.getDescription());
            company.get().setSize(companyDTO.getSize());
            companyRepository.save(company.get());
        }

    }

    @Override
    public void deleteCompany(long id) {
        companyRepository.deleteById(id);

    }

    @Override
    public List<Company> getAllCompanies() {
        Optional<List<Company>> companies = Optional.of(companyRepository.findAll());
        return companies.orElse(null);
    }

    @Override
    public List<Company> getCompaniesByLocation(String location) {
        Optional<List<Company>> companies = Optional.of(companyRepository.findAllByLocationLikeIgnoreCase(location));
        return companies.orElse(null);
    }

    @Override
    public List<Company> getCompaniesBySize(int size) {
        Optional<List<Company>> companies = Optional.of(companyRepository.findAllBySizeLessThanEqual(size));
        return companies.orElse(null);
    }
//C:\Users\lebak\Full stack\Projects\images

    //path separator is independent of the operating system
    private final String BASE_PATH = "C:" + File.separator+"Users" + File.separator + "lebak" +
            File.separator + "Full stack" + File.separator + "Projects"
            + File.separator+"images" + File.separator;

    @Override
    public String uploadImage(MultipartFile file, long id) throws IOException {
        String fileName = file.getOriginalFilename();
        String timeStamp = String.valueOf(System.currentTimeMillis());
        String  path = BASE_PATH +timeStamp+"_"+ fileName;

        file.transferTo(new File(path));

        Optional<Company> company = companyRepository.findById(id);
        if (company.isPresent()) {
            company.get().setLogo(path);
            companyRepository.save(company.get());
        }
        return path;
    }

    @Override
    public byte[] getImage(long id) throws IOException {
        Optional<Company> company = companyRepository.findById(id);
        String path = company.get().getLogo();
        return Files.readAllBytes(new File(path).toPath());
    }


}

