package george.restfulllibraryapi.dtos.requests;

import george.restfulllibraryapi.models.User;
import george.restfulllibraryapi.models.review.Review;
import george.restfulllibraryapi.models.review.ReviewId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequest {

    //id
    private BookRequest book;
    private UserRequest user;
    //data
    private String content;
    private Short score;

    public Review build() {
        return new Review()
                .setReviewId(new ReviewId()
                        .setBook(book.build())
                        .setUser(user.build())
                )
                .setContent(this.content)
                .setScore(this.score);
    }

}
