package com.akropoliprishtine.controllers.book;

import com.akropoliprishtine.dto.BookBorrowDTO;
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

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/book_book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/")
    public Book createBook(@RequestBody Book book) {
        return this.bookService.save(book);
    }

    @PutMapping("/")
    public Book updateBook(@RequestBody Book book) {
        return this.bookService.save(book);
    }

    @GetMapping("/")
    public List<BookBorrowDTO> getBooks(@RequestParam(defaultValue = "0", required = false) Integer pageNumber,
                                        @RequestParam(defaultValue = "20", required = false) Integer pageSize,
                                        @RequestParam(required = false) String bookName,
                                        @RequestParam(defaultValue = "0", required = false) int authorId,
                                        @RequestParam(required = false) String category) {

        Pageable paging = PageRequest.of(pageNumber, pageSize);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return this.bookService.getBooksPage(paging, bookName, authorId, category);
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

