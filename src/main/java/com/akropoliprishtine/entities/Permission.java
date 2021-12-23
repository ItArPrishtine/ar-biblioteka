package com.akropoliprishtine.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "account_permissions")
@Table(name = "account_permissions", uniqueConstraints = @UniqueConstraint(columnNames = {"endpoint"}))
public class Permission extends Auditable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String permission_code;

    @Column
    private String description;

    @Column
    private String endpoint;
}
