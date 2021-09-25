package george.restfulllibraryapi.controllers;

import george.restfulllibraryapi.services.BookListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<?> list(){
        return this;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> find(@PathVariable) {
        return;
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody) {

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable, @RequestBody) {

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable) {

    }
}
