package george.restfulllibraryapi.dtos.requests;

import george.restfulllibraryapi.models.Book;
import george.restfulllibraryapi.models.BookList;
import george.restfulllibraryapi.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookListRequest {

    private Long id;
    private String name;
    private UserRequest user;
    private List<BookRequest> books;

    public BookList build() {
        List<Book> newBooks = new ArrayList<>();
        for(BookRequest bookRequest : this.books)
            newBooks.add(bookRequest.build());

        return new BookList()
                .setId(this.id)
                .setName(this.name)
                .setUser(this.user.build())
                .setBooks(newBooks);
    }
}
