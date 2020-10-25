package com.akropoliprishtine.entities.book;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "book_borrow")
public class Borrow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name="borrowRequestId", nullable = false)
    @OneToOne
    private BorrowRequest borrowRequest;

    @Column(nullable = false)
    private Date borrowUntil;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BorrowRequest getBorrowRequest() {
        return borrowRequest;
    }

    public void setBorrowRequest(BorrowRequest borrowRequest) {
        this.borrowRequest = borrowRequest;
    }

    public Date getBorrowUntil() {
        return borrowUntil;
    }

    public void setBorrowUntil(Date borrowUntil) {
        this.borrowUntil = borrowUntil;
    }
}