package george.restfulllibraryapi.services;

import george.restfulllibraryapi.dtos.responses.ReviewResponse;
import george.restfulllibraryapi.models.review.Review;
import george.restfulllibraryapi.models.review.ReviewId;
import george.restfulllibraryapi.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    private ReviewRepository reviewRepository;

    @Autowired
    public void setReviewRepository(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<ReviewResponse> list() {
        List<ReviewResponse> reviewResponses = new ArrayList<>();
        for(Review review : this.reviewRepository.findAll())
            reviewResponses.add(new ReviewResponse(review));
        return reviewResponses;
    }

    public Optional<Review> find(ReviewId id) {
        return this.reviewRepository.findById(id);
    }

    public Review insert(Review review) {
        return this.reviewRepository.save(review);
    }

    public Review update(Review review) {
        return this.reviewRepository.saveAndFlush(review);
    }

    public void delete(ReviewId id) {
        this.reviewRepository.deleteById(id);
    }

}
