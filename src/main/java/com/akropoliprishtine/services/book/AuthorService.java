package com.akropoliprishtine.services.book;

import com.akropoliprishtine.entities.book.Author;
import com.akropoliprishtine.entities.book.Book;
import com.akropoliprishtine.repositories.book.AuthorRepository;
import com.akropoliprishtine.services.AmazonClient;
import com.akropoliprishtine.services.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    AmazonClient amazonClient;

    @Autowired
    AuthorRepository authorRepository;
    
    @Autowired
    ApplicationUserService userService;

    public AuthorService(AuthorRepository authorRepository,
                         AmazonClient amazonClient,
                         ApplicationUserService userService) {
        this.authorRepository = authorRepository;
        this.amazonClient = amazonClient;
        this.userService = userService;
    }

    public Author saveAuthor(Author author) {
        author.setOrganization(this.userService.getLoggedUser().getOrganization());
        return this.authorRepository.save(author);
    }

    public Author saveAndUploadFile(Author author, MultipartFile multipartFile) {
        String uploadedFileUrl = null;

        if (multipartFile != null) {
            uploadedFileUrl = amazonClient.uploadFileTos3bucket(multipartFile);
            author.setImageUrl(uploadedFileUrl);
        }

        try {
            return this.saveAuthor(author);
        } catch (Exception e) {
            if (uploadedFileUrl != null) {
                amazonClient.deleteFromS3Bucket(uploadedFileUrl);
            }
        }
        return null;
    }

    public Page<Author> getAuthorsPage(Pageable pageable) {
        // TODO BY ORG ID
        return this.authorRepository.findAll(pageable);
    }

    public Optional<Author> getAuthorDetails(Long id) {
        return this.authorRepository.findById(id);
    }

    public void deleteAuthor(Long id) {
        this.authorRepository.deleteById(id);
    }
}
