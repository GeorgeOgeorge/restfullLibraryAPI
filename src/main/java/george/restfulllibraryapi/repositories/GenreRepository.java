package george.restfulllibraryapi.repositories;

import george.restfulllibraryapi.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}