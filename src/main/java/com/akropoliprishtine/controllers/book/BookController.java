package com.akropoliprishtine.controllers.book;

import com.akropoliprishtine.dto.BookBorrowDTO;
import com.akropoliprishtine.entities.book.Author;
import com.akropoliprishtine.entities.book.Book;
import com.akropoliprishtine.enums.BookCategory;
import com.akropoliprishtine.services.book.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/book_book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/create")
    public Book createBook(@RequestParam(value = "file", required = false) MultipartFile file,
                               @RequestParam("book") String book) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        Book bookToSave = objectMapper.readValue(book, Book.class);
        return this.bookService.save(bookToSave, file);

    }

    @PutMapping("/update")
    public Book updateBook(@RequestParam(value = "file", required = false) MultipartFile file,
                           @RequestParam("book") String book) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Book bookToSave = objectMapper.readValue(book, Book.class);
        return this.bookService.save(bookToSave, file);
    }

    @GetMapping("/read")
    public List<BookBorrowDTO> getBooks(@RequestParam(defaultValue = "0", required = false) Integer pageNumber,
                                        @RequestParam(defaultValue = "20", required = false) Integer pageSize,
                                        @RequestParam(required = false) String bookName,
                                        @RequestParam(defaultValue = "0", required = false) int authorId,
                                        @RequestParam(defaultValue = "0", required = false) int organization,
                                        @RequestParam(required = false) String category) {

        Pageable paging = PageRequest.of(pageNumber, pageSize);
        return this.bookService.getBooksPage(paging, bookName, authorId, category, organization);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBook(@PathVariable Long id) {
        this.bookService.delete(id);
    }


    @GetMapping("/read/author/{authorId}")
    public List<Book> getAuthorBooks(@PathVariable String authorId) {
        return this.bookService.getBooksByAuthor(authorId);
    }

    @GetMapping("/read/{id}")
    public Optional<Book> getBookDetails(@PathVariable Long id) {
        return this.bookService.getBooksById(id);
    }
}

