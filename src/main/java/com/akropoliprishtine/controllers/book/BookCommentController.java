package com.akropoliprishtine.controllers.book;

import com.akropoliprishtine.entities.book.BookComment;
import com.akropoliprishtine.services.book.BookCommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book_comment")
public class BookCommentController {

    @Autowired
    private BookCommentsService bookCommentsService;

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        this.bookCommentsService.delete(id);
    }

    @PostMapping("/")
    public BookComment addComment(@RequestBody BookComment comment) {
        return this.bookCommentsService.addComment(comment);
    }

    @PutMapping("/")
    public BookComment editComment(@RequestBody BookComment comment) {
        return this.bookCommentsService.updateComment(comment);
    }

    @GetMapping("/book/{bookId}")
    public List<BookComment> getAuthorBooks(@PathVariable Long bookId) {
        return this.bookCommentsService.getCommentsByBook(bookId);
    }
}

