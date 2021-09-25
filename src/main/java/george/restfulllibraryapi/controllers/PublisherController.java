package george.restfulllibraryapi.controllers;

import george.restfulllibraryapi.services.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/publisher")
@CrossOrigin(origins = "http://localhost:3000", exposedHeaders = "X-Total-Count")
public class PublisherController {

    private PublisherService publisherService;

    @Autowired
    public void setPublisherService(PublisherService publisherService) {
        this.publisherService = publisherService;
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
