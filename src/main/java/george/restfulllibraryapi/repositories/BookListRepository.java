package george.restfulllibraryapi.repositories;

import george.restfulllibraryapi.models.BookList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookListRepository extends JpaRepository<BookList, Long> {
}