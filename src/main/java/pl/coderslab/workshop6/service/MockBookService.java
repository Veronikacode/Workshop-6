package pl.coderslab.workshop6.service;

import org.springframework.stereotype.Component;
import pl.coderslab.workshop6.entity.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MockBookService {

    private List<Book> books;

    public MockBookService() {
        books = new ArrayList<>();
        books.add(new Book(1L, "9788324631766", "Thinking in Java", "Bruce	Eckel", "Helion", "programming"));
        books.add(new Book(2L, "9788324627738", "Rusz	glowa	Java.", "Sierra	Kathy,	Bates	Bert", "Helion",
                "programming"));
        books.add(new Book(3L, "9780130819338", "Java	2.	Podstawy", "Cay	Horstmann,	Gary	Cornell", "Helion",
                "programming"));
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public Optional<Book> getBookById(Long id) {
        return books.stream()
                .filter(book -> id != null && id.equals(book.getId()))
                .findFirst();
    }

    public boolean updateBook(Long id, Book updatedBook) {
        Optional<Book> optionalBook = getBookById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            book.setIsbn(updatedBook.getIsbn());
            book.setPublisher(updatedBook.getPublisher());
            book.setType(updatedBook.getType());
            return true;
        }
        return false;
    }

    public boolean deleteBook(Long id) {
        return books.removeIf(book -> book.getId() != null && book.getId().equals(id));
    }

    public void addBook(Book book) {
        books.add(book);
    }
}
