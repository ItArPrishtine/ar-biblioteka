package com.akropoliprishtine.services.book;

import com.akropoliprishtine.entities.book.Author;
import com.akropoliprishtine.repositories.book.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    
    public List<Author> saveAuthors(List<Author> authors) {
        return this.authorRepository.saveAll(authors);
    }

    public Author saveAuthor(Author author) {
        return this.authorRepository.save(author);
    }

    public List<Author> getAuthors() {
        return this.authorRepository.findAll();
    }
}
