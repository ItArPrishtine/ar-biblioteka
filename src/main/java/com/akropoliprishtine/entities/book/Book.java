package com.akropoliprishtine.entities.book;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Setter
@Entity(name="books")
//@Table(name = "book_book", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "category"}))
//@SQLDelete(sql = "UPDATE book_book SET deleted = true WHERE id=?")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String title;

    @Column(name = "author")
    private Integer author;

    @Column(name = "category")
    private String category;

    @Column(name = "publishing_house")
    private String publishingHouse;

    @Column(name = "publication_year")
    private String publicationYear;
}
