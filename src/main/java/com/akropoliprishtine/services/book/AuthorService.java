package com.akropoliprishtine.services.book;

import com.akropoliprishtine.entities.ApplicationUser;
import com.akropoliprishtine.entities.Organization;
import com.akropoliprishtine.entities.book.Author;
import com.akropoliprishtine.entities.book.Book;
import com.akropoliprishtine.enums.UserRolesEnum;
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
    
    @Autowired
    OrganizationService organizationService;

    public AuthorService(AuthorRepository authorRepository,
                         AmazonClient amazonClient,
                         OrganizationService organizationService,
                         ApplicationUserService userService) {
        this.authorRepository = authorRepository;
        this.amazonClient = amazonClient;
        this.userService = userService;
        this.organizationService = organizationService;
    }

    public Author saveAuthor(Author author) {
        author.setOrganization(this.userService.getLoggedUser().getOrganization());
        return this.authorRepository.save(author);
    }
    
    public Author saveAuthorFromSheet(Author author) {
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

    public List<Author> getAuthorsPage(long organization) {
        ApplicationUser loggedUser = this.userService.getLoggedUser();

        Organization org;

        if ((loggedUser.getRole().getName().equals(UserRolesEnum.KK.label) ||
                loggedUser.getRole().getName().equals(UserRolesEnum.ADMIN.label) &&
                        organization != 0)
        ) {
            org = organizationService.getOrganizationById(organization);
        } else {
            org = loggedUser.getOrganization();
        }
        
        return this.authorRepository.findAllByOrganization(org);
    }
    
    public List<Author> getAuthors() {
        return this.authorRepository.findAll();
    }

    public Optional<Author> getAuthorDetails(Long id) {
        return this.authorRepository.findById(id);
    }

    public void deleteAuthor(Long id) {
        this.authorRepository.deleteById(id);
    }
}
