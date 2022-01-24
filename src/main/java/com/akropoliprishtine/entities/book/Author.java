package com.akropoliprishtine.entities.book;

import com.akropoliprishtine.entities.Auditable;
import com.akropoliprishtine.entities.Organization;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name="book_author")
@Table(name = "book_author", uniqueConstraints = @UniqueConstraint(columnNames = {"firstName", "lastName"}))

public class Author extends Auditable<Long> {
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

    @OneToOne
    @JoinColumn(name = "organizationId", nullable = true)
    private Organization organization;
}
