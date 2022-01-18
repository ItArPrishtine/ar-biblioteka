package com.akropoliprishtine.entities.book;

import com.akropoliprishtine.entities.Auditable;
import com.akropoliprishtine.entities.Organization;
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

    @OneToOne
    @JoinColumn(name = "organizationId", nullable = true)
    private Organization organization;
}
