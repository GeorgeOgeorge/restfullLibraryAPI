package george.restfulllibraryapi.models.review;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Data
@Table(name = "review")
@Entity
public class Review {

    @EmbeddedId
    @AssociationOverrides({
            @AssociationOverride(name = "book", joinColumns = @JoinColumn(name = "review_id_book_ibns")),
            @AssociationOverride(name = "user", joinColumns = @JoinColumn(name = "review_id_user_id"))
    })
    private ReviewId reviewId;

    @Column(name = "content", nullable = false, length = 280)
    private String content;

    @Column(name = "score", nullable = false)
    private Short score;



}