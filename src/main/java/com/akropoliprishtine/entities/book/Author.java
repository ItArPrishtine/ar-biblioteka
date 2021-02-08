package com.akropoliprishtine.entities.book;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "book_author", uniqueConstraints = @UniqueConstraint(columnNames = {"firstName", "lastName"}))

public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = true)
    private String dateOfBirth;

    @Column(nullable = true)
    private String dateOfDeath;

    @Column(nullable = true, length = 20000)
    private String description;

    @Column(nullable = true)
    private String imageUrl;
}