package com.akropoliprishtine.controllers;

import com.akropoliprishtine.entities.book.Author;
import com.akropoliprishtine.entities.book.Book;
import com.akropoliprishtine.services.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/book_book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/author/{authorId}")
    public List<Book> getAuthorBooks(@PathVariable String authorId) {
        return this.bookService.getBooksByAuthor(authorId);
    }

    @GetMapping("/list")
    public Page<Book> getAuthors(@RequestParam(defaultValue = "0") Integer pageNumber,
                                 @RequestParam(defaultValue = "20") Integer pageSize) {

        Pageable paging = PageRequest.of(pageNumber, pageSize);
        return this.bookService.getBooksPage(paging);
    }

    @GetMapping("details/{id}")
    public Optional<Book> getBookDetails(@PathVariable Long id) {
        return this.bookService.getBooksDetails(id);
    }
}

