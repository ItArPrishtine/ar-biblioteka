package com.akropoliprishtine.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "account_permissions")
@Table(name = "account_permissions")
public class Permission extends Auditable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String permission_code;

    @ManyToMany(targetEntity = Role.class, cascade = CascadeType.ALL )
    private List<Role> roles;

    @Column
    private String description;

    @Column
    private String endpoint;
}
