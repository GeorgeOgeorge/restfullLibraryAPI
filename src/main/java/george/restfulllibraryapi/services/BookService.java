package george.restfulllibraryapi.services;

import george.restfulllibraryapi.dtos.responses.BookResponse;
import george.restfulllibraryapi.models.Book;
import george.restfulllibraryapi.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private BookRepository bookRepository;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookResponse> list() {
        List<BookResponse> bookResponses = new ArrayList<>();
        for(Book book : this.bookRepository.findAll())
            bookResponses.add(new BookResponse(book));
        return bookResponses;
    }

    public Optional<Book> find(Long id) {
        return this.bookRepository.findById(id);
    }

    public Book save(Book book) {
       return this.bookRepository.save(book);
    }

    public Book update(Book book) {
        return this.bookRepository.saveAndFlush(book);
    }

    public void delete(Long id) {
        this.bookRepository.deleteById(id);
    }

}
