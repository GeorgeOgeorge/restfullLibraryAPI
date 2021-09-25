package george.restfulllibraryapi.dtos.responses;

import george.restfulllibraryapi.controllers.GenreController;
import george.restfulllibraryapi.models.Book;
import george.restfulllibraryapi.models.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse extends RepresentationModel<BookResponse> {

    private Long ibns;
    private String name;
    private Short pageNumber;
    private String coverPictureURI;
    private PublisherResponse publisher;
    private AuthorResponse author;
    private List<GenreResponse> genres;
    private Date startRead;
    private Date finishRead;

    public BookResponse(Book book) {

        List<GenreResponse> newGenres = new ArrayList<>();
        for(Genre genre : book.getGenres())
            genres.add(new GenreResponse(genre));

        this.ibns = book.getIbns();
        this.name = book.getName();
        this.pageNumber = book.getPageNumber();
        this.coverPictureURI = book.getCoverPictureURI();
        this.publisher = new PublisherResponse(book.getPublisher());
        this.author = new AuthorResponse(book.getAuthor());
        this.genres = newGenres;
        this.startRead = book.getStartRead();
        this.finishRead = book.getFinishRead();

        this.add(linkTo(GenreController.class).slash(book.getIbns()).withSelfRel());
        this.add(linkTo(GenreController.class).slash("/" + book.getIbns()).withRel("edit book"));
        this.add(linkTo(GenreController.class).slash("/" + book.getIbns()).withRel("delete book"));
        this.add(linkTo(GenreController.class).withRel("All books"));
    }
}
