package org.example.gradgateway1.Rest;


import org.example.gradgateway1.DTO.ReviewsDTO;
import org.example.gradgateway1.Services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/review")
public class ReviewRestController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewRestController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/add")
    public String addReview(@RequestBody ReviewsDTO reviewsDTO) {
        reviewService.addReview(reviewsDTO);
        return "Review added";
    }

    @PutMapping("/update/{id}")
    public String updateReview(@RequestBody ReviewsDTO reviewsDTO, @PathVariable long id) {
        reviewService.updateReview(reviewsDTO, id);
        return "Review updated";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteReview(@PathVariable long id) {
        reviewService.deleteReview(id);
        return "Review deleted";
    }

    @GetMapping("/reviews")
    public String getReviews() {
        return reviewService.getReviews().toString();
    }

    @GetMapping("/review/{id}")
    public String getReview(@PathVariable long id) {
        return reviewService.getReviewById(id).toString();
    }

    @GetMapping("/reviews/company/{id}/{order}")
    public String getReviewByCompanyIdOrderBy(@PathVariable long id, @PathVariable String order) {
        return reviewService.getReviewByCompanyIdOrderBy(id, order).toString();
    }
}
