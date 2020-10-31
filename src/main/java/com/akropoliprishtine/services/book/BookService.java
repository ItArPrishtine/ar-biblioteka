package com.akropoliprishtine.services.book;

import com.akropoliprishtine.entities.Role;
import com.akropoliprishtine.entities.book.Book;
import com.akropoliprishtine.repositories.RoleRepository;
import com.akropoliprishtine.repositories.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
    public List<Book> saveBooks(List<Book> books) {
        return this.bookRepository.saveAll(books);
    }

    public List<Book> getBooks() {
        return this.bookRepository.findAll();
    }
}
