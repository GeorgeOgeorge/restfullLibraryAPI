package george.restfulllibraryapi.dtos.responses;

import george.restfulllibraryapi.controllers.GenreController;
import george.restfulllibraryapi.models.Author;
import george.restfulllibraryapi.models.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorResponse extends RepresentationModel<AuthorResponse> {

    private String name;
    private String socialNetwork;
    private String personalWebSite;
    private String primaryLanguage;
    private String pictureURI;
    private List<BookResponse> books;

    public AuthorResponse(Author author) {
        List<BookResponse> newBooks = new ArrayList<>();
        for(Book book : author.getBooks())
            newBooks.add(new BookResponse(book));

        this.name = author.getName();
        this.socialNetwork = author.getSocialNetwork();
        this.personalWebSite = author.getPersonalWebSite();
        this.primaryLanguage = author.getPrimaryLanguage();
        this.pictureURI = author.getPictureURI();
        this.books = newBooks;

        this.add(linkTo(GenreController.class).slash(author.getId()).withSelfRel());
        this.add(linkTo(GenreController.class).slash("/" + author.getId()).withRel("edit author"));
        this.add(linkTo(GenreController.class).slash("/" + author.getId()).withRel("delete author"));
        this.add(linkTo(GenreController.class).withRel("All authors"));
    }
}
