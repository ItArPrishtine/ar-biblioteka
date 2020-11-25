package com.akropoliprishtine.controllers;

import com.akropoliprishtine.entities.book.Book;
import com.akropoliprishtine.services.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/book_book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/author/{authorId}")
    public List<Book> getAuthorBooks(@PathVariable String authorId) {
        return this.bookService.getBooksByAuthor(authorId);
    }
}

