package com.akropoliprishtine.repositories.economy;

import com.akropoliprishtine.entities.ApplicationUser;
import com.akropoliprishtine.entities.economy.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findAllByApplicationUser(ApplicationUser user);
}