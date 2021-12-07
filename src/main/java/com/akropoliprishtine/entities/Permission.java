package com.akropoliprishtine.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

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

    @Column
    private String description;

    @ManyToMany(mappedBy = "permissions", fetch = FetchType.EAGER)
    Set<Role> roles;

    @Column
    private String endpoint;
}
