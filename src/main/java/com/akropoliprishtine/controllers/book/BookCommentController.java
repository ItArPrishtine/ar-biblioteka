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

    @GetMapping("/read/book/{bookId}")
    public List<BookComment> getBookComments(@PathVariable Long bookId) {
        return this.bookCommentsService.getCommentsByBook(bookId);
    }

    @PostMapping("/create")
    public BookComment addComment(@RequestBody BookComment comment) {
        return this.bookCommentsService.addComment(comment);
    }

    @PutMapping("/update")
    public BookComment editComment(@RequestBody BookComment comment) {
        return this.bookCommentsService.updateComment(comment);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBook(@PathVariable Long id) {
        this.bookCommentsService.delete(id);
    }
}

