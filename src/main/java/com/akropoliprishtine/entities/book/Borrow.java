package com.akropoliprishtine.entities.book;

import com.akropoliprishtine.entities.ApplicationUser;
import com.akropoliprishtine.entities.Auditable;
import com.akropoliprishtine.entities.Organization;
import com.akropoliprishtine.enums.BorrowStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity(name="book_borrow")
@Table(name = "book_borrow")
@SQLDelete(sql = "UPDATE book_borrow SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Borrow extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private ApplicationUser applicationUser;

    @JoinColumn(name="bookId", nullable = false)
    @OneToOne
    private Book book;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MMM/yyyy")
    private Date borrowFrom;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MMM/yyyy")
    private Date borrowUntil;

    @Column(nullable = true)
    @JsonFormat(pattern = "dd/MMM/yyyy")
    private Date returnedDate;

    @Column(nullable = false)
    private BorrowStatus borrowStatus;

    @Column(nullable = false)
    private Boolean extendedDeadline = false;

    @OneToOne
    @JoinColumn(name = "organizationId", nullable = true)
    private Organization organization;
}
