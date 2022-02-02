package com.akropoliprishtine.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "account_permissions")
@Table(name = "account_permissions", uniqueConstraints = @UniqueConstraint(columnNames = {"endpoint"}))
@SQLDelete(sql = "UPDATE account_permissions SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Permission extends Auditable<String> {
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
