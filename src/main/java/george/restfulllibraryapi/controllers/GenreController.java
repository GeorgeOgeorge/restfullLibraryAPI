package george.restfulllibraryapi.controllers;

import george.restfulllibraryapi.dtos.requests.GenreRequest;
import george.restfulllibraryapi.dtos.responses.GenreResponse;
import george.restfulllibraryapi.models.Genre;
import george.restfulllibraryapi.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/genre")
@CrossOrigin(origins = "http://localhost:3000", exposedHeaders = "X-Total-Count")
public class GenreController {

    private GenreService genreService;

    @Autowired
    public void setGenreService(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public List<GenreResponse> list(){
        return this.genreService.list();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<GenreResponse> find(@PathVariable Long id) {
        Optional<Genre> newGenre = this.genreService.find(id);
        if(newGenre.isPresent())
            return ResponseEntity.ok().body(new GenreResponse(newGenre.get()));
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<GenreResponse> insert(@RequestBody GenreRequest genreRequest) {
        Genre newGenre = this.genreService.insert(genreRequest.build());
        return ResponseEntity.created(URI.create("genre/" + newGenre.getId())).body(new GenreResponse(newGenre));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<GenreResponse> update(@PathVariable Long id, @RequestBody Genre genre) {
       Optional<Genre> newGenre = this.genreService.find(id);
       if(newGenre.isPresent() && newGenre.get().getId().equals(genre.getId()))
           return ResponseEntity.ok(new GenreResponse(this.genreService.update(genre)));
       else
           return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if(this.genreService.find(id).isPresent()) {
            this.genreService.delete(id);
            return ResponseEntity.ok(linkTo(GenreController.class).withRel("All Genres"));
        }
        else
            return ResponseEntity.notFound().build();
    }

}
