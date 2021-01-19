package com.akropoliprishtine.controllers.book;

import com.akropoliprishtine.dto.BookBorrowDTO;
import com.akropoliprishtine.entities.book.Book;
import com.akropoliprishtine.services.book.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Tuple;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/book_book")
public class BookController {

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
    public List<BookBorrowDTO> getBooks(@RequestParam(defaultValue = "0") Integer pageNumber,
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
        return this.bookService.getBooksById(id);
    }
}

