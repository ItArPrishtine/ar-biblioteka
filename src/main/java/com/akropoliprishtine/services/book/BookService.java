package com.akropoliprishtine.services.book;

import com.akropoliprishtine.dto.BookBorrowDTO;
import com.akropoliprishtine.entities.book.Author;
import com.akropoliprishtine.entities.book.Book;
import com.akropoliprishtine.enums.BorrowStatus;
import com.akropoliprishtine.repositories.book.BookRepository;
import com.akropoliprishtine.services.AmazonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorService authorService;

    @Autowired
    AmazonClient amazonClient;

    @Autowired
    EntityManager entityManager;

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

    public List<BookBorrowDTO> getBooksPage(Pageable pageable, String bookName, int authorId, String category) {
        StringBuilder queryBuilder = new StringBuilder("SELECT book.id, book.name, book.category, " +
                "book.publicationYear, author.firstName, author.lastName, author.id as authorId " +
                "FROM book_book book " +
                "inner join book_author author on book.author.id = author.id ");

        List<String> conditions = new ArrayList<>();

        if (bookName != null && !bookName.isEmpty()) {
            conditions.add("lower(book.name) LIKE '%" + bookName.toLowerCase() + "%'");
        }

        if (authorId != 0) {
            conditions.add("author.id = " + authorId);
        }

        if (category != null && !category.isEmpty()) {
            conditions.add("lower(category) LIKE '%" + category.toLowerCase() + "%'");
        }

        String conditionsToString = String.join(" AND ", conditions);

        if (conditionsToString.length() != 0) {
            queryBuilder.append( " WHERE " + conditionsToString);
        }

        Query query = entityManager.createQuery(queryBuilder.toString());

        query.setFirstResult((int)pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        return query.getResultList();
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
