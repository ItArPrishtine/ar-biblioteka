package com.akropoliprishtine.services.economy;


import com.akropoliprishtine.entities.ApplicationUser;
import com.akropoliprishtine.entities.economy.Payment;
import com.akropoliprishtine.repositories.UserRepository;
import com.akropoliprishtine.repositories.economy.PaymentRepository;
import com.akropoliprishtine.services.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    UserRepository userRepository;

    public PaymentService(PaymentRepository paymentRepository,
                          UserRepository userRepository) {
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;
    }

    public List<Payment> getPayments(Long userId) {
        if (userId != 0) {
            Optional<ApplicationUser> user = this.userRepository.findById(userId);
            return this.paymentRepository.findAllByApplicationUser(user.get());
        }
        return this.paymentRepository.findAll();
    }

    public Optional<Payment> getPaymentById(Long id) {
        return this.paymentRepository.findById(id);
    }

    public Payment createPayment(Payment payment) {
        return this.paymentRepository.save(payment);
    }

    public Payment updatePayment(Payment payment) {
        return this.paymentRepository.save(payment);
    }

    public void deletePayment(Long id) {
        this.paymentRepository.deleteById(id);
    }
}
