package com.akropoliprishtine.entities.book;


import com.akropoliprishtine.entities.Auditable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name="book_book")
@Table(name = "book_book", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "category"}))
public class Book extends Auditable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String description;

    @JoinColumn(name = "authorId", nullable = false)
    @OneToOne
    private Author author;

    @JoinColumn(name = "editionId", nullable = false)
    @OneToOne
    private Edition edition;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "shelf")
    private String shelf;

    private String publicationYear;
}
