package pl.coderslab.workshop6.service;

import pl.coderslab.workshop6.entity.Book;

import java.util.List;
import java.util.Optional;

public interface MockBookService {
    List<Book> getAllBooks();

    Optional<Book> getBookById(Long id);

    boolean updateBook(Long id, Book updatedBook);

    boolean deleteBook(Long id);

    void addBook(Book book);
}
