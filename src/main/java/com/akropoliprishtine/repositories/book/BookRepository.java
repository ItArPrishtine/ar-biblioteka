package com.akropoliprishtine.repositories.book;

import com.akropoliprishtine.entities.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}