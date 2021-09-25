package george.restfulllibraryapi.controllers;

import george.restfulllibraryapi.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<?> list(){
        return this.genreService.list();
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
