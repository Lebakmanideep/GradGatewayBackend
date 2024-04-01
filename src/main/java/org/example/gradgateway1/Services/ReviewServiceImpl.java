package org.example.gradgateway1.Services;

import org.example.gradgateway1.DAO.CompanyRepository;
import org.example.gradgateway1.DAO.ReviewsRepository;
import org.example.gradgateway1.DAO.UserRepository;
import org.example.gradgateway1.DTO.CompanyDTO;
import org.example.gradgateway1.DTO.ReviewsDTO;
import org.example.gradgateway1.Entity.Company;
import org.example.gradgateway1.Entity.Reviews;
import org.example.gradgateway1.Entity.User;
import org.example.gradgateway1.Util.AuthenticationDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewsRepository reviewsRepository;

    private final CompanyRepository companyRepository;

    private final AuthenticationDetails authenticationDetails;

    @Autowired
    public ReviewServiceImpl(ReviewsRepository reviewsRepository, CompanyRepository companyRepository, AuthenticationDetails authenticationDetails) {
        this.reviewsRepository = reviewsRepository;
        this.companyRepository = companyRepository;
        this.authenticationDetails = authenticationDetails;
    }

    @Override
    public void addReview(ReviewsDTO reviewsDTO) {
        Company company = companyRepository.findById(reviewsDTO.getCompanyId()).orElse(null);
        Optional<User> user = authenticationDetails.getUser();
        if (company == null || user.isEmpty()) {
            throw new RuntimeException("Company or User not found");
        }
        Reviews reviews = Reviews.builder()
                .id(0L)
                .description(reviewsDTO.getDescription())
                .rating(reviewsDTO.getRating())
                .date(new Date())
                .company(company)
                .user(user.get())
                .build();
        reviewsRepository.save(reviews);
}

@Override
public void deleteReview(Long id) {
    reviewsRepository.deleteById(id);

}

@Override
public void updateReview(ReviewsDTO reviewsDTO, long id) {
    Reviews reviews = reviewsRepository.findById(id).orElse(null);
    if (reviews != null) {
        reviews.setDescription(reviewsDTO.getDescription());
        reviews.setRating(reviewsDTO.getRating());
        reviewsRepository.save(reviews);
    }
}

@Override
public List<Reviews> getReviews() {
    return reviewsRepository.findAll();
}

@Override
public Reviews getReviewById(Long id) {
    return reviewsRepository.findById(id).orElse(null);
}

@Override
public List<Reviews> getReviewByCompanyIdOrderBy(Long id, String order) {
    if (order.equals("asc"))
        return reviewsRepository.findAllByCompanyIdOrderByWorst(id);
    return reviewsRepository.findAllByCompanyIdOrderByBest(id);
}
}
