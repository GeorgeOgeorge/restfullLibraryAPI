package george.restfulllibraryapi.services;

import george.restfulllibraryapi.dtos.responses.GenreResponse;
import george.restfulllibraryapi.models.Genre;
import george.restfulllibraryapi.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    private GenreRepository genreRepository;

    @Autowired
    public void setGenreRepository(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<GenreResponse> list() {
        List<GenreResponse> genreResponses = new ArrayList<>();
        for(Genre genre : this.genreRepository.findAll())
            genreResponses.add(new GenreResponse(genre));
        return genreResponses;
    }

    public Optional<Genre> find(Long id) {
        return this.genreRepository.findById(id);
    }

    public Genre insert(Genre genre) {
        return this.genreRepository.save(genre);
    }

    public Genre update(Genre genre) {
        return this.genreRepository.saveAndFlush(genre);
    }

    public void delete(Long id) {
        this.genreRepository.deleteById(id);
    }

}
