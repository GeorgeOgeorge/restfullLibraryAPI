package george.restfulllibraryapi.services;

import george.restfulllibraryapi.dtos.responses.PublisherResponse;
import george.restfulllibraryapi.models.Publisher;
import george.restfulllibraryapi.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PublisherService {

    private PublisherRepository publisherRepository;

    @Autowired
    public void setPublisherRepository(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public List<PublisherResponse> list() {
        List<PublisherResponse> publisherResponses = new ArrayList<>();
        for(Publisher publisher : this.publisherRepository.findAll())
            publisherResponses.add(new PublisherResponse(publisher));
        return publisherResponses;
    }

    public Optional<Publisher> find(Long id) {
        return this.publisherRepository.findById(id);
    }

    public Publisher save(Publisher publisher) {
        return this.publisherRepository.save(publisher);
    }

    public Publisher update(Publisher publisher) {
        return this.publisherRepository.saveAndFlush(publisher);
    }

    public void delete(Long id) {
        this.publisherRepository.deleteById(id);
    }
}
