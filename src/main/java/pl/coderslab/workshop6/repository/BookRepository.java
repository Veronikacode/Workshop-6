package pl.coderslab.workshop6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.workshop6.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}


