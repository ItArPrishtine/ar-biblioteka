package com.akropoliprishtine.repositories.book;

import com.akropoliprishtine.entities.book.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {


    @Query(value = "SELECT * from book_author ORDER by first_name",
            nativeQuery = true)
    List<Author> findAllOrderByName();

}