package com.akropoliprishtine.services.book;

import com.akropoliprishtine.entities.Role;
import com.akropoliprishtine.entities.book.Author;
import com.akropoliprishtine.entities.book.Book;
import com.akropoliprishtine.repositories.RoleRepository;
import com.akropoliprishtine.repositories.book.BookRepository;
import com.akropoliprishtine.services.AmazonClient;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorService authorService;

    @Autowired
    AmazonClient amazonClient;

    public BookService(BookRepository bookRepository,
                       AuthorService authorService,
                       AmazonClient amazonClient
                       ) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.amazonClient = amazonClient;
    }
    
    public List<Book> saveBooks(List<Book> books) {
        return this.bookRepository.saveAll(books);
    }

    public List<Book> getBooks() {
        return this.bookRepository.findAll();
    }

    public Page<Book> getBooksPage(Pageable pageable) {
        return this.bookRepository.findAll(pageable);
    }

    public Optional<Book> getBooksDetails(Long id) {
        return this.bookRepository.findById(id);
    }

    public List<Book> getBooksByAuthor(String authorId) {
        Optional<Author> author = authorService.getAuthorDetails(Long.parseLong(authorId));
        return this.bookRepository.findBooksByAuthor(author);
    }

    public Book save(Book book) {
        return this.bookRepository.save(book);
    }

    public Book saveAndUploadFile(Book book, MultipartFile multipartFile) {
        String uploadedFileUrl = null;

        if (multipartFile != null) {
            uploadedFileUrl = amazonClient.uploadFileTos3bucket(multipartFile);
            book.setImageUrl(uploadedFileUrl);
        }

        try {
            book.setImageUrl(uploadedFileUrl);
            return this.save(book);
        } catch (Exception e) {
            if (uploadedFileUrl != null) {
                amazonClient.deleteFromS3Bucket(uploadedFileUrl);
            }
        }
        return null;
    }

    public void delete(Long bookId) {
        this.bookRepository.deleteById(bookId);
    }
}
