package com.akropoliprishtine.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "account_users")
@Table(name = "account_users")
@SQLDelete(sql = "UPDATE account_users SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class ApplicationUser extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true, nullable = false)
    private String username;

    @Column(unique=true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String dateOfBirth;

    @Column()
    private String description;

    @Column()
    private String eSign;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password", nullable = false)
    private String password;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String passwordConfirm;

    @OneToOne
    @JoinColumn(name = "roleId", nullable = false)
    private Role role;

    @OneToOne
    @JoinColumn(name = "organizationId", nullable = true)
    private Organization organization;

    @Column(name = "professionalLabels", nullable = true)
    private String professionalLabels;

    @Column(name = "allowProfessionalEmail", nullable = false, columnDefinition = "boolean default false")
    private Boolean allowProfessionalEmail = false;
}
