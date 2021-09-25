package george.restfulllibraryapi.controllers;

import george.restfulllibraryapi.dtos.requests.PublisherRequest;
import george.restfulllibraryapi.dtos.responses.PublisherResponse;
import george.restfulllibraryapi.models.Publisher;
import george.restfulllibraryapi.services.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
    public List<PublisherResponse> list(){
        return this.publisherService.list();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PublisherResponse> find(@PathVariable Long id) {
        Optional<Publisher> newPublisher = this.publisherService.find(id);
        if(newPublisher.isPresent())
            return ResponseEntity.ok().body(new PublisherResponse(newPublisher.get()));
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<PublisherResponse> insert(@RequestBody PublisherRequest publisherRequest) {
        Publisher newPublisher = this.publisherService.insert(publisherRequest.build());
        return ResponseEntity.created(URI.create("publisher/" + newPublisher.getRegisterNumber())).body(new PublisherResponse(newPublisher));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PublisherResponse> update(@PathVariable Long id, @RequestBody Publisher publisher) {
        Optional<Publisher> newPublisher = this.publisherService.find(id);
        if(newPublisher.isPresent() && newPublisher.get().getRegisterNumber().equals(publisher.getRegisterNumber()))
            return ResponseEntity.ok().body(new PublisherResponse(this.publisherService.update(publisher)));
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if(this.publisherService.find(id).isPresent()) {
            this.publisherService.delete(id);
            return ResponseEntity.ok(linkTo(PublisherController.class).withRel("All Publisher"));
        }
        else
            return ResponseEntity.notFound().build();
    }
}
