package org.example.gradgateway1.Rest;

import org.example.gradgateway1.DAO.CompanyRepository;
import org.example.gradgateway1.DTO.CompanyDTO;
import org.example.gradgateway1.Entity.Company;
import org.example.gradgateway1.Services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyRestController {
    private final CompanyService companyService;

    @Autowired
    public CompanyRestController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/companies")
    public List<Company> getCompanies() {
        return companyService.getAllCompanies();
    }

    @GetMapping("/company/{id}")
    public Company getCompany(@PathVariable long id) {
        return companyService.getCompanyById(id);
    }

    @PostMapping("/add")
     String addCompany(@RequestBody CompanyDTO companyDTO) {
         companyService.addCompany(companyDTO);
         return "Company added";
    }

    @PutMapping("/update/{id}")
    public String updateCompany(@PathVariable long id, @RequestBody CompanyDTO companyDTO) {
        companyService.updateCompany(companyDTO, id);
        return "Company updated";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCompany(@PathVariable long id) {
        companyService.deleteCompany(id);
        return "Company deleted";
    }

    @GetMapping("/companies/location/{location}")
    public List<Company> getCompaniesByLocation(@RequestParam String location) {
        return companyService.getCompaniesByLocation(location);
    }

    @GetMapping("/companies/size/{size}")
    public List<Company> getCompaniesBySize(@RequestParam int size) {
        return companyService.getCompaniesBySize(size);
    }

    @PostMapping("/upload/{id}")
    public String uploadImage(@RequestParam MultipartFile file, @PathVariable long id) throws IOException {
        return companyService.uploadImage(file, id);
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<?> downloadImage(@PathVariable long id) throws IOException {
        byte [] imageData = companyService.getImage(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }




}
