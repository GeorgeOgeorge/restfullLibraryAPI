package george.restfulllibraryapi.services;

import george.restfulllibraryapi.dtos.responses.BookListResponse;
import george.restfulllibraryapi.models.BookList;
import george.restfulllibraryapi.repositories.BookListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookListService {

    private BookListRepository bookListRepository;

    @Autowired
    public void setBookListRepository(BookListRepository bookListRepository) {
        this.bookListRepository = bookListRepository;
    }

    public List<BookListResponse> list() {
        List<BookListResponse> bookListResponses = new ArrayList<>();
        for(BookList bookList : this.bookListRepository.findAll())
            bookListResponses.add(new BookListResponse(bookList));
        return bookListResponses;
    }

    public Optional<BookList> find(Long id) {
        return this.bookListRepository.findById(id);
    }

    public BookList save(BookList bookList) {
        return this.bookListRepository.save(bookList);
    }

    public BookList update(BookList bookList) {
        return this.bookListRepository.saveAndFlush(bookList);
    }

    public void delete(Long id) {
        this.bookListRepository.deleteById(id);
    }

}
