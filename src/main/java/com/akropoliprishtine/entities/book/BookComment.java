package com.akropoliprishtine.entities.book;

import com.akropoliprishtine.entities.ApplicationUser;
import com.akropoliprishtine.entities.Auditable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "book_comment")
@Getter
@Setter
public class BookComment extends Auditable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String comment;

    @JoinColumn(name = "userId", nullable = false)
    @ManyToOne
    private ApplicationUser applicationUser;

    @JoinColumn(name = "bookId", nullable = false)
    @ManyToOne
    private Book book;
    

}
