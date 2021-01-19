package com.akropoliprishtine.services.book;

import com.akropoliprishtine.entities.book.Book;
import com.akropoliprishtine.entities.book.BookComment;
import com.akropoliprishtine.repositories.book.BookCommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BookCommentsService {

    @Autowired
    BookCommentsRepository bookCommentsRepository;

    @Autowired
    BookService bookService;

    public BookCommentsService(BookCommentsRepository bookCommentsRepository,
                               BookService bookService) {
        this.bookCommentsRepository = bookCommentsRepository;
        this.bookService = bookService;
    }

    public BookComment addComment(BookComment comment) {
        return this.bookCommentsRepository.save(comment);
    }

    public BookComment updateComment(BookComment comment) {
        return this.bookCommentsRepository.save(comment);
    }

    public void delete(Long commentId) {
        this.bookCommentsRepository.deleteById(commentId);
    }

    public List<BookComment> getCommentsByBook(Long bookId) {
        Optional<Book> book = this.bookService.getBooksById(bookId);
        return this.bookCommentsRepository.findBookCommentByBook(book);
    }
}
