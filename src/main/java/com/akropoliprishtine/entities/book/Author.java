package com.akropoliprishtine.entities.book;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name="authors")
//@SQLDelete(sql = "UPDATE book_author SET deleted = true WHERE id=?")
//@Where(clause = "deleted=false")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
}
