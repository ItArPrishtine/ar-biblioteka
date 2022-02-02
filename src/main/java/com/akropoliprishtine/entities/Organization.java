package com.akropoliprishtine.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "account_organization")
@Table(name = "account_organization", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
@SQLDelete(sql = "UPDATE account_organization SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Organization extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true, nullable = false)
    private String name;
}
