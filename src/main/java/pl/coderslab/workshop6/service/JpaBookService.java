package pl.coderslab.workshop6.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pl.coderslab.workshop6.entity.Book;
import pl.coderslab.workshop6.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
@Primary
@RequiredArgsConstructor
public class JpaBookService implements BookService {

    private final BookRepository bookRepository;

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> get(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public void add(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void update(Book book) {
        bookRepository.save(book);
    }
}


