package george.restfulllibraryapi.controllers;

import george.restfulllibraryapi.dtos.requests.AuthorRequest;
import george.restfulllibraryapi.dtos.responses.AuthorResponse;
import george.restfulllibraryapi.models.Author;
import george.restfulllibraryapi.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/author")
@CrossOrigin(origins = "http://localhost:3000", exposedHeaders = "X-Total-Count")
public class AuthorController {

    private AuthorService authorService;

    @Autowired
    public void setAuthorService(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<AuthorResponse> list(){
        return this.authorService.list();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AuthorResponse> find(@PathVariable Long id) {
        Optional<Author> newAuthor = this.authorService.find(id);
        if(newAuthor.isPresent())
            return ResponseEntity.ok().body(new AuthorResponse(newAuthor.get()));
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<AuthorResponse> insert(@RequestBody AuthorRequest authorRequest) {
        Author author = this.authorService.insert(authorRequest.build());
        return ResponseEntity.created(URI.create("author/" + author.getId())).body(new AuthorResponse(author));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AuthorResponse> update(@PathVariable Long id, @RequestBody Author author) {
        Optional<Author> newAuthor = this.authorService.find(id);
        if(newAuthor.isPresent() && newAuthor.get().getId().equals(author.getId()))
            return ResponseEntity.ok().body(new AuthorResponse(this.authorService.update(author)));
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if(this.authorService.find(id).isPresent()) {
            this.authorService.delete(id);
            return ResponseEntity.ok(linkTo(AuthorController.class).withRel("All Author"));
        }
        else
            return ResponseEntity.notFound().build();
    }

}