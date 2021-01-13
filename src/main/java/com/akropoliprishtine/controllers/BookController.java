package com.akropoliprishtine.controllers;

import com.akropoliprishtine.entities.book.Author;
import com.akropoliprishtine.entities.book.Book;
import com.akropoliprishtine.services.AmazonClient;
import com.akropoliprishtine.services.book.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@RestController
@RequestMapping("/book_book")
public class BookController {

    private final Path booksDirectory = Paths.get("src/main/resources/images/books");

    @Autowired
    private BookService bookService;

    @PostMapping("/")
    public Book createBook(@RequestParam(value = "file", required = false) MultipartFile file,
                           @RequestParam("book") String book) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Book bookToSave = objectMapper.readValue(book, Book.class);

        return this.bookService.saveAndUploadFile(bookToSave, file);
    }

    @PutMapping("/")
    public Book updateBook(@RequestParam(value = "file", required = false) MultipartFile file,
                           @RequestParam("book") String book) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        Book bookToSave = objectMapper.readValue(book, Book.class);

        return this.bookService.saveAndUploadFile(bookToSave, file);
    }

    @GetMapping("/")
    public Page<Book> getBooks(@RequestParam(defaultValue = "0") Integer pageNumber,
                               @RequestParam(defaultValue = "20") Integer pageSize) {

        Pageable paging = PageRequest.of(pageNumber, pageSize);
        return this.bookService.getBooksPage(paging);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        this.bookService.delete(id);
    }


    @GetMapping("/author/{authorId}")
    public List<Book> getAuthorBooks(@PathVariable String authorId) {
        return this.bookService.getBooksByAuthor(authorId);
    }

    @GetMapping("/{id}")
    public Optional<Book> getBookDetails(@PathVariable Long id) {
        return this.bookService.getBooksDetails(id);
    }
}

