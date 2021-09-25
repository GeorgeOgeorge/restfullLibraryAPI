package george.restfulllibraryapi.services;

import george.restfulllibraryapi.dtos.responses.AuthorResponse;
import george.restfulllibraryapi.models.Author;
import george.restfulllibraryapi.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private AuthorRepository authorRepository;

    @Autowired
    public void setAuthorRepository(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    public List<AuthorResponse> list() {
        List<AuthorResponse> authorResponses = new ArrayList<>();
        for(Author author : this.authorRepository.findAll())
            authorResponses.add(new AuthorResponse(author));
        return authorResponses;
    }

    public Optional<Author> find(Long id) {
        return this.authorRepository.findById(id);
    }

    public Author insert(Author author) {
        return this.authorRepository.save(author);
    }

    public Author update(Author author) {
        return this.authorRepository.saveAndFlush(author);
    }

    public void delete(Long id) {
        this.authorRepository.deleteById(id);
    }

}
