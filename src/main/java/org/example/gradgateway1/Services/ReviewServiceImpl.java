package org.example.gradgateway1.Services;

import org.example.gradgateway1.DAO.CompanyRepository;
import org.example.gradgateway1.DAO.ReviewsRepository;
import org.example.gradgateway1.DTO.CompanyDTO;
import org.example.gradgateway1.DTO.ReviewsDTO;
import org.example.gradgateway1.Entity.Company;
import org.example.gradgateway1.Entity.Reviews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class ReviewServiceImpl implements ReviewService{

    private final ReviewsRepository reviewsRepository;

    private final CompanyRepository companyRepository;

    @Autowired
    public ReviewServiceImpl(ReviewsRepository reviewsRepository, CompanyRepository companyRepository) {
        this.reviewsRepository = reviewsRepository;
        this.companyRepository = companyRepository;
    }
    @Override
    public void addReview(ReviewsDTO reviewsDTO) {
        Company company = companyRepository.findById(reviewsDTO.getCompanyId()).orElse(null);
        if (company != null) {
            Reviews reviews = Reviews.builder()
                    .id(0L)
                    .description(reviewsDTO.getDescription())
                    .rating(reviewsDTO.getRating())
                    .date(new Date())
                    .company(company)
                    .user(null)
                    .build();
            reviewsRepository.save(reviews);
        }
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
