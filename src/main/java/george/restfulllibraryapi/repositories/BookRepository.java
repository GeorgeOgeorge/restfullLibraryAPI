package george.restfulllibraryapi.repositories;

import george.restfulllibraryapi.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}