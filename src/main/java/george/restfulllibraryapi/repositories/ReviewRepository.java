package george.restfulllibraryapi.repositories;

import george.restfulllibraryapi.models.review.Review;
import george.restfulllibraryapi.models.review.ReviewId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, ReviewId> {
}