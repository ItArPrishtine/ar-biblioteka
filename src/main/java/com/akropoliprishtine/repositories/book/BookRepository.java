package com.akropoliprishtine.repositories.book;

import com.akropoliprishtine.entities.book.Author;
import com.akropoliprishtine.entities.book.Book;
import com.akropoliprishtine.enums.BorrowStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findBooksByAuthor(Optional<Author> author);

    @Query(value = "SELECT book.id, book.name, book.category, book.image_url, book.publication_year, author.first_name, author.last_name, author.id as authorId, bb.borrow_status " +
            "FROM book_book book " +
            "inner join book_author author on book.author_id = author.id " +
            "left join book_borrow bb on book.id = bb.book_id " +
            "where lower(book.name) like CONCAT('%', ?1 ,'%') " +
            "OFFSET ?2 LIMIT ?3 ",
            nativeQuery = true)

    List<Tuple> findAll(String bookName, long offset, long limit);

    @Query(value = "SELECT book.id, book.name, book.category, book.image_url, book.publication_year, author.first_name, author.last_name, author.id as authorId, bb.borrow_status " +
            "FROM book_book book " +
            "inner join book_author author on book.author_id = author.id " +
            "left join book_borrow bb on book.id = bb.book_id " +
            "where lower(book.name) like CONCAT('%', ?1 ,'%') AND author.id = ?2 " +
            "OFFSET ?3 LIMIT ?4 ",
            nativeQuery = true)
    List<Tuple> findAllByAuthor(String bookName, long authorId, long offset, long limit);
}
