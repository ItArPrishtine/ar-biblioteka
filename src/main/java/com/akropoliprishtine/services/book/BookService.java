package com.akropoliprishtine.services.book;

import com.akropoliprishtine.dto.BookBorrowDTO;
import com.akropoliprishtine.entities.book.Author;
import com.akropoliprishtine.entities.book.Book;
import com.akropoliprishtine.repositories.book.BookRepository;
import com.akropoliprishtine.services.AmazonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Tuple;
import java.util.ArrayList;
import java.util.List;
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

    public List<BookBorrowDTO> getBooksPage(Pageable pageable, String bookName, long authorId, String category) {
        List<Tuple> tuples;

        if (authorId == 0) {
            tuples = this.bookRepository.findAll(bookName, pageable.getOffset(), pageable.getPageSize());
        } else {
            tuples = this.bookRepository.findAllByAuthor(bookName, authorId, pageable.getOffset(), pageable.getPageSize());
        }
        List<BookBorrowDTO> bookListDTOBorrow = new ArrayList<>();

        for (Tuple tuple : tuples) {
            BookBorrowDTO bookBorrowDTO = new BookBorrowDTO();
            bookBorrowDTO.setId(tuple.get(0).toString());
            bookBorrowDTO.setName(tuple.get(1).toString());
            bookBorrowDTO.setCategory(tuple.get(2).toString());
            bookBorrowDTO.setAuthorFirstName(tuple.get(5).toString());
            bookBorrowDTO.setAuthorLastName(tuple.get(6).toString());
            bookBorrowDTO.setAuthorId(tuple.get(7).toString());

            if (tuple.get(3) != null) {
                bookBorrowDTO.setImageUrl(tuple.get(3).toString());
            } else {
                bookBorrowDTO.setImageUrl("");
            }
            if (tuple.get(4) != null) {
                bookBorrowDTO.setPublicationYear(tuple.get(4).toString());
            } else {
                bookBorrowDTO.setPublicationYear("");
            }
            if (tuple.get(8) != null) {
                bookBorrowDTO.setBorrowStatus(tuple.get(8).toString());
            } else {
                bookBorrowDTO.setBorrowStatus("");
            }

            bookListDTOBorrow.add(bookBorrowDTO);
        }
        return bookListDTOBorrow;
    }

    public Optional<Book> getBooksById(Long id) {
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
