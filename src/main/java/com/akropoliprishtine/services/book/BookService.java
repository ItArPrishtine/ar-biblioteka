package com.akropoliprishtine.services.book;

import com.akropoliprishtine.dto.BookBorrowDTO;
import com.akropoliprishtine.entities.ApplicationUser;
import com.akropoliprishtine.entities.book.Author;
import com.akropoliprishtine.entities.book.Book;
import com.akropoliprishtine.enums.BookCategory;
import com.akropoliprishtine.enums.BorrowStatus;
import com.akropoliprishtine.enums.UserRolesEnum;
import com.akropoliprishtine.repositories.book.BookRepository;
import com.akropoliprishtine.services.AmazonClient;
import com.akropoliprishtine.services.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    
    @Autowired
    ApplicationUserService userService;

    public BookService(BookRepository bookRepository,
                       AuthorService authorService,
                       AmazonClient amazonClient,
                       ApplicationUserService userService
                       ) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.amazonClient = amazonClient;
        this.userService = userService;
    }

    public List<Book> saveBooks(List<Book> books) {
        return this.bookRepository.saveAll(books);
    }

    public List<Book> getBooks() {
        return this.bookRepository.findAll();
    }

    public List<BookBorrowDTO> getBooksPage(Pageable pageable, String bookName, int authorId, String category, int organization) {
        ApplicationUser loggedUser = this.userService.getLoggedUser();
        
        StringBuilder queryBuilder = new StringBuilder("SELECT book.id, book.name, book.category, book.imageUrl, " +
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

            if (category.equals(BookCategory.ANGLEZE.label)) {
                conditions.add("category NOT LIKE '%" + BookCategory.AKROPOLI.label + "%'");
                conditions.add("category NOT LIKE '%" + BookCategory.AKROPOLI2.label + "%'");
            }
        }
        
        
        int organizationId;
        
        if ((loggedUser.getRole().getName().equals(UserRolesEnum.KK.label) ||
                loggedUser.getRole().getName().equals(UserRolesEnum.ADMIN.label) && 
                organization != 0)        
        ) {
            organizationId = organization;
        } else {
            organizationId = loggedUser.getOrganization().getId().intValue();
        }

        conditions.add("book.organization.id = " + organizationId);

        String conditionsToString = String.join(" AND ", conditions);

        if (conditionsToString.length() != 0) {
            queryBuilder.append( " WHERE " + conditionsToString);
        }

        queryBuilder.append(" ORDER BY book.category DESC");

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

    public Book save(Book book, MultipartFile multipartFile) {
        book.setOrganization(this.userService.getLoggedUser().getOrganization());

        this.uploadImageAndSaveBook(book, multipartFile);
        return this.bookRepository.save(book);
    }
    
    public Book update(Book book) {
        book.setOrganization(this.userService.getLoggedUser().getOrganization());
        return this.bookRepository.save(book);
    }

    public Book uploadImageAndSaveBook(Book book, MultipartFile multipartFile) {
        String uploadedFileUrl = null;

        try {
            if (multipartFile != null) {
                uploadedFileUrl = amazonClient.uploadFileTos3bucket(multipartFile);
                book.setImageUrl(uploadedFileUrl);
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
