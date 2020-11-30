package com.akropoliprishtine.services.book;

import com.akropoliprishtine.entities.book.Author;
import com.akropoliprishtine.repositories.book.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author saveAuthor(Author author) {
        return this.authorRepository.save(author);
    }

    public List<Author> getAuthors() {
        return this.authorRepository.findAll();
    }

    public Page<Author> getAuthorsPage(Pageable pageable) {
        return this.authorRepository.findAll(pageable);
    }

    public Optional<Author> getAuthorDetails(Long id) {
        return this.authorRepository.findById(id);
    }

    public void deleteAuthor(Long id) {
        this.authorRepository.deleteById(id);
    }
}
