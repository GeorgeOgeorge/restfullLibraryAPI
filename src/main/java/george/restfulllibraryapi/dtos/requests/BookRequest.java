package george.restfulllibraryapi.dtos.requests;

import george.restfulllibraryapi.models.Book;
import george.restfulllibraryapi.models.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {

    private Long ibns;
    private String name;
    private Short pageNumber;
    private String coverPictureURI;
    private PublisherRequest publisher;
    private AuthorRequest author;
    private List<GenreRequest> genres;
    private Date startRead;
    private Date finishRead;

    public Book build() {
        List<Genre> newGenres = new ArrayList<>();
        for(GenreRequest genreRequest : genres)
            newGenres.add(genreRequest.build());

        return new Book()
                .setIbns(this.ibns)
                .setName(this.name)
                .setPageNumber(this.pageNumber)
                .setCoverPictureURI(this.coverPictureURI)
                .setPublisher(this.publisher.build())
                .setAuthor(this.author.build())
                .setGenres(newGenres)
                .setStartRead(this.startRead)
                .setFinishRead(this.finishRead);
    }

}
