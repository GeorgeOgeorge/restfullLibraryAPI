package george.restfulllibraryapi.dtos.responses;

import george.restfulllibraryapi.controllers.GenreController;
import george.restfulllibraryapi.models.Book;
import george.restfulllibraryapi.models.BookList;
import george.restfulllibraryapi.models.User;
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
public class BookListResponse extends RepresentationModel<BookListResponse> {

    private String name;
    private UserResponse user;
    private List<BookResponse> books;

    public BookListResponse(BookList bookList) {

        List<BookResponse> newBooks = new ArrayList<>();
        for(Book book : bookList.getBooks())
            newBooks.add(new BookResponse(book));

        this.name = bookList.getName();
        this.user = new UserResponse(bookList.getUser());
        this.books = newBooks;

        this.add(linkTo(GenreController.class).slash(bookList.getId()).withSelfRel());
        this.add(linkTo(GenreController.class).slash("/" + bookList.getId()).withRel("edit bookList"));
        this.add(linkTo(GenreController.class).slash("/" + bookList.getId()).withRel("delete bookList"));
        this.add(linkTo(GenreController.class).withRel("All bookLists"));
    }
}
