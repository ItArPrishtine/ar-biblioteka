package com.akropoliprishtine.entities;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "account_roles", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
@SQLDelete(sql = "UPDATE account_roles SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Role extends Auditable<String> implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    public String getAuthority() {
        return name;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "account_role_permissions",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    Set<Permission> permissions;

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

    public Set<Permission> getPermissions() {
        return this.permissions;
    }
}
