package com.akropoliprishtine.entities.book;


import com.akropoliprishtine.entities.ApplicationUser;
import com.akropoliprishtine.enums.BorrowRequestStatus;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "book_borrow_request", uniqueConstraints = @UniqueConstraint(columnNames = {"userId"}))
public class BorrowRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name="userId", nullable = false)
    @OneToOne
    private ApplicationUser applicationUser;

    @JoinColumn(name="bookId", nullable = false)
    @OneToOne
    private Book book;

    @Column(nullable = false)
    private Date borrowFrom;

    @Column(nullable = false)
    private BorrowRequestStatus borrowRequestStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getBorrowFrom() {
        return borrowFrom;
    }

    public void setBorrowFrom(Date borrowFrom) {
        this.borrowFrom = borrowFrom;
    }

    public BorrowRequestStatus getBorrowRequestStatus() {
        return borrowRequestStatus;
    }

    public void setBorrowRequestStatus(BorrowRequestStatus borrowRequestStatus) {
        this.borrowRequestStatus = borrowRequestStatus;
    }
}