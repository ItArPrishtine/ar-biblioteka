package com.akropoliprishtine.repositories.book;

import com.akropoliprishtine.entities.book.Book;
import com.akropoliprishtine.entities.book.BookComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookCommentsRepository extends JpaRepository<BookComment, Long> {
    List<BookComment> findBookCommentByBook(Optional<Book> book);
}