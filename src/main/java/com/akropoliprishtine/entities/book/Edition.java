package com.akropoliprishtine.entities.book;

import com.akropoliprishtine.entities.Auditable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "book_edition", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class Edition extends Auditable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String description;
}
