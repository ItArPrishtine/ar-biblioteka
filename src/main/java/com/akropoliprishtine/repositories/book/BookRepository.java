package com.akropoliprishtine.repositories.book;

import com.akropoliprishtine.entities.book.Author;
import com.akropoliprishtine.entities.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findBooksByAuthor(Optional<Author> author);
}