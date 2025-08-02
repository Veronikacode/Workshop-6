package pl.coderslab.workshop6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.workshop6.entity.Book;
import pl.coderslab.workshop6.service.MockBookService;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final MockBookService bookService;
    private Long nextId = 1L;

    @Autowired
    public BookController(MockBookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id).orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
    }

    @GetMapping("/helloBook")
    public Book helloBook() {
        return new Book(1L, "9788324631766", "Thinking in Java",
                "Bruce Eckel", "Helion", "programming");
    }

    @PostMapping
    public String addBook(@RequestBody Book newBook) {
        newBook.setId(nextId++);
        bookService.addBook(newBook);
        return "Book added successfully";
    }

    @PutMapping
    public ResponseEntity<String> updateBook(@RequestBody Book updatedBook) {
        if (updatedBook.getId() == null) {
            return ResponseEntity.badRequest().body("Book id must be provided");
        }
        boolean updated = bookService.updateBook((updatedBook.getId()), updatedBook);
        if (updated) {
            return ResponseEntity.ok("Book updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable("id") Long id) {
        boolean deleted = bookService.deleteBook(id);
        if (deleted) {
            return ResponseEntity.ok("Book deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
        }
    }

}
