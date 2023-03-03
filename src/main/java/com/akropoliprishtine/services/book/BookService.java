package com.akropoliprishtine.services.book;

import com.akropoliprishtine.entities.book.Author;
import com.akropoliprishtine.entities.book.Book;
import com.akropoliprishtine.enums.BookCategory;
import com.akropoliprishtine.enums.UserRolesEnum;
import com.akropoliprishtine.repositories.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorService authorService;

//    @Autowired
//    AmazonClient amazonClient;

    @Autowired
    EntityManager entityManager;
    
//    @Autowired
//    ApplicationUserService userService;

    public BookService(BookRepository bookRepository,
                       AuthorService authorService
//                       AmazonClient amazonClient,
//                       ApplicationUserService userService
                       ) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
//        this.amazonClient = amazonClient;
//        this.userService = userService;
    }

    public List<Book> saveBooks(List<Book> books) {
        return this.bookRepository.saveAll(books);
    }

    public List<Book> getBooks() {
        return this.bookRepository.findAll();
    }

    public Optional<Book> getBooksById(Long id) {
        return this.bookRepository.findById(id);
    }

    public List<Book> getBooksByAuthor(String authorId) {
        Optional<Author> author = authorService.getAuthorDetails(Long.parseLong(authorId));
        return this.bookRepository.findBooksByAuthor(author);
    }

    public Book save(Book book, MultipartFile multipartFile) {
//        book.setOrganization(this.userService.getLoggedUser().getOrganization());

//        this.uploadImageAndSaveBook(book, multipartFile);
        return this.bookRepository.save(book);
    }
    
    public Book update(Book book) {
//        book.setOrganization(this.userService.getLoggedUser().getOrganization());
        return this.bookRepository.save(book);
    }

    public Book uploadImageAndSaveBook(Book book, MultipartFile multipartFile) {
        String uploadedFileUrl = null;

        try {
            if (multipartFile != null) {
//                uploadedFileUrl = amazonClient.uploadFileTos3bucket(multipartFile);
//                book.setImageUrl(uploadedFileUrl);
            }
            
            return this.bookRepository.save(book);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void delete(Long bookId) {
        this.bookRepository.deleteById(bookId);
    }
}
