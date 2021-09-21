package com.akropoliprishtine.entities.economy;

import com.akropoliprishtine.entities.ApplicationUser;
import com.akropoliprishtine.entities.BaseEntity;
import com.akropoliprishtine.enums.PayedMonth;
import com.akropoliprishtine.enums.PayedYear;
import com.akropoliprishtine.enums.PaymentType;
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

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private PayedMonth payedMonth;

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private PayedYear payedYear;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = true)
    private String description;

    @Column(nullable = true)
    private Boolean verifiedFromUser;

    @Column(nullable = true)
    private Boolean verifiedFromEconomy;
}
