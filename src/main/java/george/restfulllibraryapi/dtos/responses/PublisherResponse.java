package george.restfulllibraryapi.dtos.responses;

import george.restfulllibraryapi.controllers.GenreController;

import george.restfulllibraryapi.models.Book;
import george.restfulllibraryapi.models.Publisher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublisherResponse extends RepresentationModel<PublisherResponse> {

    private String name;
    private Long registerNumber;
    private List<BookResponse> books;

    public PublisherResponse(Publisher publisher) {
        List<BookResponse> newBooks = new ArrayList<>();
        for(Book book : publisher.getBooks())
            newBooks.add(new BookResponse(book));

        this.registerNumber = publisher.getRegisterNumber();
        this.name = publisher.getName();
        this.books = newBooks;

        this.add(linkTo(GenreController.class).slash(publisher.getRegisterNumber()).withSelfRel());
        this.add(linkTo(GenreController.class).slash("/" + publisher.getRegisterNumber()).withRel("edit publisher"));
        this.add(linkTo(GenreController.class).slash("/" + publisher.getRegisterNumber()).withRel("delete publisher"));
        this.add(linkTo(GenreController.class).withRel("All publishers"));
    }
}
