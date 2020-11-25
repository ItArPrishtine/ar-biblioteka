package com.akropoliprishtine.services.book;

import com.akropoliprishtine.entities.Role;
import com.akropoliprishtine.entities.book.Author;
import com.akropoliprishtine.entities.book.Book;
import com.akropoliprishtine.repositories.RoleRepository;
import com.akropoliprishtine.repositories.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    AuthorService authorService;

    public BookService(BookRepository bookRepository,
                       AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }
    
    public List<Book> saveBooks(List<Book> books) {
        return this.bookRepository.saveAll(books);
    }

    public List<Book> getBooks() {
        return this.bookRepository.findAll();
    }

    public List<Book> getBooksByAuthor(String authorId) {
        Optional<Author> author = authorService.getAuthorDetails(Long.parseLong(authorId));
        return this.bookRepository.findBooksByAuthor(author);
    }
}
