package george.restfulllibraryapi.controllers;

import george.restfulllibraryapi.dtos.requests.BookListRequest;
import george.restfulllibraryapi.dtos.responses.BookListResponse;
import george.restfulllibraryapi.models.BookList;
import george.restfulllibraryapi.services.BookListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/booklist")
@CrossOrigin(origins = "http://localhost:3000", exposedHeaders = "X-Total-Count")
public class BookListController {

    private BookListService bookListService;

    @Autowired
    public void setBookListService(BookListService bookListService) {
        this.bookListService = bookListService;
    }

    @GetMapping
    public List<BookListResponse> list(){
        return this.bookListService.list();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BookListResponse> find(@PathVariable Long id) {
        Optional<BookList> newBookList = this.bookListService.find(id);
        if(newBookList.isPresent())
            return ResponseEntity.ok().body(new BookListResponse(newBookList.get()));
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<BookListResponse> insert(@RequestBody BookListRequest bookListRequest) {
        BookList bookList = this.bookListService.insert(bookListRequest.build());
        return ResponseEntity.created(URI.create("booklist/" + bookList.getId())).body(new BookListResponse(bookList));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<BookListResponse> update(@PathVariable Long id, @RequestBody BookList bookList) {
        Optional<BookList> newBookList = this.bookListService.find(id);
        if(newBookList.isPresent() && newBookList.get().getId().equals(bookList.getId()))
            return ResponseEntity.ok().body(new BookListResponse(this.bookListService.update(bookList)));
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if(this.bookListService.find(id).isPresent()) {
            this.bookListService.delete(id);
            return ResponseEntity.ok(linkTo(BookListController.class).withRel("All BookLists"));
        }
        else
            return ResponseEntity.notFound().build();
    }
}
