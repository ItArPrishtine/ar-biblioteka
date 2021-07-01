package com.akropoliprishtine.entities.economy;

import com.akropoliprishtine.entities.ApplicationUser;
import com.akropoliprishtine.entities.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "economy_payment")
@Table(name = "economy_payment")
@Getter
@Setter
public class Payment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "userId", nullable = false)
    @ManyToOne
    private ApplicationUser applicationUser;

    @Column(nullable = false)
    private Date paymentDate;

    @Column(nullable = false)
    private Date payedMonth;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = true)
    private String description;
}