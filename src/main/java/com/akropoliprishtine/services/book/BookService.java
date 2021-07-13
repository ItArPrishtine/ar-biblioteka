package com.akropoliprishtine.services.book;

import com.akropoliprishtine.dto.BookBorrowDTO;
import com.akropoliprishtine.entities.book.Author;
import com.akropoliprishtine.entities.book.Book;
import com.akropoliprishtine.enums.BookCategory;
import com.akropoliprishtine.repositories.book.BookRepository;
import com.akropoliprishtine.services.AmazonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

        if (authorId == 0 && bookName.isEmpty() && category.isEmpty()) {
            tuples = this.bookRepository.findAll(pageable.getOffset(), pageable.getPageSize());
        } else if (authorId == 0 && category.isEmpty()) {
            tuples = this.bookRepository.findAllByBookName(bookName.toLowerCase(), pageable.getOffset(), pageable.getPageSize());
        } else if (bookName == null && category.isEmpty()){
            tuples = this.bookRepository.findAllByAuthor(authorId, pageable.getOffset(), pageable.getPageSize());
        } else if (category != null && (authorId == 0 && bookName.isEmpty())) {
            tuples = this.bookRepository.findAllByCategory(category, pageable.getOffset(), pageable.getPageSize());
        } else if (category != null && bookName != null && authorId == 0) {
            tuples = this.bookRepository.findAllByCategoryAndBookName(category, bookName.toLowerCase(), pageable.getOffset(), pageable.getPageSize());
        } else if (category != null && bookName != null && authorId > 0) {
            tuples = this.bookRepository.findAllByAuthorAndBookNameAndCategory(bookName.toLowerCase(), category, authorId, pageable.getOffset(), pageable.getPageSize());
        } else if (category != null && bookName == null && authorId > 0) {
            tuples = this.bookRepository.findAllByAuthorAndCategory(authorId, category, pageable.getOffset(), pageable.getPageSize());
        }else {
            tuples = this.bookRepository.findAllByAuthorAndBookName(bookName.toLowerCase(), authorId, pageable.getOffset(), pageable.getPageSize());
        }

        List<BookBorrowDTO> bookListDTOBorrow = new ArrayList<>();

        for (Tuple tuple : tuples) {
            BookBorrowDTO bookBorrowDTO = new BookBorrowDTO();
            bookBorrowDTO.setId(tuple.get(0).toString());
            bookBorrowDTO.setName(tuple.get(1).toString());
            bookBorrowDTO.setCategory(tuple.get(2).toString());
            bookBorrowDTO.setAuthorFirstName(tuple.get(4).toString());
            bookBorrowDTO.setAuthorLastName(tuple.get(5).toString());
            bookBorrowDTO.setAuthorId(tuple.get(6).toString());

            if (tuple.get(3) != null) {
                bookBorrowDTO.setPublicationYear(tuple.get(3).toString());
            } else {
                bookBorrowDTO.setPublicationYear("");
            }
            if (tuple.get(7) != null) {
                bookBorrowDTO.setBorrowStatus(tuple.get(7).toString());
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

    public void delete(Long bookId) {
        this.bookRepository.deleteById(bookId);
    }
}
