package com.akropoliprishtine.entities.book;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "book_book", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "category"}))
public class Book {
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

    @Column(nullable = true)
    private String imageUrl;

    private String publicationYear;
}