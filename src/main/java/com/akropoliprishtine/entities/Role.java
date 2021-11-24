package com.akropoliprishtine.entities;

import org.hibernate.mapping.Set;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "account_roles")
public class Role extends Auditable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToMany(targetEntity = Permission.class, mappedBy = "roles", cascade = CascadeType.ALL)
    private List<Permission> permissions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
