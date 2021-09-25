package george.restfulllibraryapi.controllers;

import george.restfulllibraryapi.dtos.requests.BookRequest;
import george.restfulllibraryapi.dtos.responses.BookResponse;
import george.restfulllibraryapi.models.Book;
import george.restfulllibraryapi.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/book")
@CrossOrigin(origins = "http://localhost:3000", exposedHeaders = "X-Total-Count")
public class BookController {

    private BookService bookService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookResponse> list(){
        return this.bookService.list();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BookResponse> find(@PathVariable Long id) {
       Optional<Book> newBook = this.bookService.find(id);
       if(newBook.isPresent())
           return ResponseEntity.ok().body(new BookResponse(newBook.get()));
       else
           return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<BookResponse> insert(@RequestBody BookRequest bookRequest) {
        Book book = this.bookService.insert(bookRequest.build());
        return ResponseEntity.created(URI.create("book/" + book.getIbns())).body(new BookResponse(book));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<BookResponse> update(@PathVariable Long id, @RequestBody Book book) {
        Optional<Book> newBook = this.bookService.find(id);
        if(newBook.isPresent() && newBook.get().getIbns().equals(book.getIbns()))
            return ResponseEntity.ok().body(new BookResponse(this.bookService.update(book)));
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if(this.bookService.find(id).isPresent()){
            this.bookService.delete(id);
            return ResponseEntity.ok(linkTo(BookController.class).withRel("All Books"));
        }
        else
            return ResponseEntity.notFound().build();
    }
}
