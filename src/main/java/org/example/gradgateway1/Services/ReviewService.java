package org.example.gradgateway1.Services;

import org.example.gradgateway1.DTO.ReviewsDTO;
import org.example.gradgateway1.Entity.Reviews;

import java.util.List;

public interface ReviewService {
    void addReview(ReviewsDTO reviewsDTO);
    void deleteReview(Long id);
    void updateReview(ReviewsDTO reviewsDTO, long id);

    List<Reviews> getReviews();

    Reviews getReviewById(Long id);

    List<Reviews> getReviewByCompanyIdOrderBy(Long id, String order);



}
