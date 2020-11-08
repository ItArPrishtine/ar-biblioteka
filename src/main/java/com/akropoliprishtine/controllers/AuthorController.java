package com.akropoliprishtine.controllers;

import com.akropoliprishtine.entities.Role;
import com.akropoliprishtine.entities.book.Author;
import com.akropoliprishtine.services.RoleService;
import com.akropoliprishtine.services.book.AuthorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/book_author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;
    private final Path root = Paths.get("" +
            "C:\\Users\\PC\\Desktop\\projects\\akropoli-prishtine\\akropoli-prishtine\\src\\main\\resources\\regjistri.xlsx");

    @PostMapping("create")
    public Author createAuthor(@RequestParam("file") MultipartFile file,
                               @RequestParam("author") String author) throws IOException {
        System.out.println(author);
        ObjectMapper objectMapper = new ObjectMapper();
        Author authorToSave = objectMapper.readValue(author, Author.class);

        Files.copy(file.getInputStream(), this.root.resolve(Objects.requireNonNull(file.getOriginalFilename())));
        Path filePath = root.resolve(file.getOriginalFilename());
        authorToSave.setImageUrl(filePath.toString());

        return this.authorService.saveAuthor(authorToSave);
    }

    @GetMapping("list")
    public List<Author> getAuthors() {
        return this.authorService.getAuthors();
    }
}
