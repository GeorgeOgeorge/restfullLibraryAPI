package george.restfulllibraryapi.dtos.responses;

import george.restfulllibraryapi.controllers.GenreController;
import george.restfulllibraryapi.models.review.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.hateoas.RepresentationModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponse extends RepresentationModel<ReviewResponse> {

    private BookResponse book;
    private UserResponse user;
    private String content;
    private Short score;


    public ReviewResponse(Review review) {

        this.book = new BookResponse(review.getReviewId().getBook());
        this.user = new UserResponse(review.getReviewId().getUser());
        this.content = review.getContent();
        this.score = review.getScore();

        this.add(linkTo(GenreController.class).slash(review.getReviewId().getUser().getId() + "/" + review.getReviewId().getBook().getIbns()).withSelfRel());
        this.add(linkTo(GenreController.class).slash("/" + review.getReviewId().getUser().getId() + "/" + review.getReviewId().getBook().getIbns()).withRel("edit review"));
        this.add(linkTo(GenreController.class).slash("/" + review.getReviewId().getUser().getId() + "/" + review.getReviewId().getBook().getIbns()).withRel("delete review"));
        this.add(linkTo(GenreController.class).withRel("All genres"));
    }
}
