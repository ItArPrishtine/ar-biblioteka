package com.akropoliprishtine.entities.book;


import com.akropoliprishtine.entities.Auditable;
import com.akropoliprishtine.entities.Organization;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Setter
@Entity(name="book_book")
@Table(name = "book_book", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "category"}))
@SQLDelete(sql = "UPDATE book_book SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Book extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 4000)
    private String description;
    
    @Column(name = "imageUrl", nullable = false)
    private String imageUrl;

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

    @OneToOne
    @JoinColumn(name = "organizationId")
    private Organization organization;
}
