package com.akropoliprishtine.controllers.book;

import com.akropoliprishtine.entities.book.Author;
import com.akropoliprishtine.services.book.AuthorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/book_author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @PostMapping("/create")
    public Author createAuthor(@RequestParam(value = "file", required = false) MultipartFile file,
                               @RequestParam("author") String author) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Author authorToSave = objectMapper.readValue(author, Author.class);

        return this.authorService.saveAndUploadFile(authorToSave, file);
    }

    @GetMapping("/read")
    public Page<Author> getAuthors(@RequestParam(defaultValue = "0") Integer pageNumber,
                                   @RequestParam(defaultValue = "20") Integer pageSize) {

        Pageable paging = PageRequest.of(pageNumber, pageSize);
        return this.authorService.getAuthorsPage(paging);
    }

    @PutMapping("/update")
    public Author updateAuthor(@RequestParam(value = "file", required = false) MultipartFile file,
                               @RequestParam("author") String author) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Author authorToSave = objectMapper.readValue(author, Author.class);

        return this.authorService.saveAndUploadFile(authorToSave, file);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        this.authorService.deleteAuthor(id);
    }

    @GetMapping("/read/{id}")
    public Optional<Author> getAuthorDetails(@PathVariable Long id) {
        return this.authorService.getAuthorDetails(id);
    }
}
