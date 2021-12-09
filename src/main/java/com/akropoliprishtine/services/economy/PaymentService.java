package com.akropoliprishtine.services.economy;


import com.akropoliprishtine.entities.ApplicationUser;
import com.akropoliprishtine.entities.economy.Payment;
import com.akropoliprishtine.repositories.UserRepository;
import com.akropoliprishtine.repositories.economy.PaymentRepository;
import com.akropoliprishtine.services.ApplicationUserService;
import com.akropoliprishtine.services.EmailService;
import com.akropoliprishtine.services.JwtUserDetailsService;
import com.akropoliprishtine.services.RoleService;
import com.akropoliprishtine.utils.JwtTokenUtil;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    PaymentRepository paymentRepository;
    UserRepository userRepository;
    JwtUserDetailsService jwtUserDetailsService;
    ApplicationUserService userService;
    EmailService emailService;
    JwtTokenUtil jwtTokenUtil;

    public PaymentService(PaymentRepository paymentRepository,
                          UserRepository userRepository,
                          JwtUserDetailsService jwtUserDetailsService,
                          ApplicationUserService userService,
                          EmailService emailService,
                          JwtTokenUtil jwtTokenUtil) {
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.emailService = emailService;
        this.userService = userService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public List<Payment> getPayments() {
        ApplicationUser user = jwtUserDetailsService.getUserFromToken();
        String roleName = user.getRole().getName().toLowerCase();

        if ((roleName.contains("pg") && roleName.contains("ekonomia"))
                || roleName.contains("pishtari")
                || roleName.contains("kf")) {
            return this.paymentRepository.findAll();
        }

        return null;
    }

    public Optional<Payment> getPaymentById(Long id) {
        ApplicationUser user = jwtUserDetailsService.getUserFromToken();
        String roleName = user.getRole().getName().toLowerCase();

        if ((roleName.contains("pg") && roleName.contains("ekonomia"))
                || roleName.contains("pishtari")
                || roleName.contains("kf")) {
            return this.paymentRepository.findById(id);
        }
        return Optional.empty();
    }

    @Transactional
    public Payment createPayment(Payment payment) {
        ApplicationUser appUser = jwtUserDetailsService.getUserFromToken();
        String roleName = appUser.getRole().getName().toLowerCase();

        if ((roleName.contains("pg") && roleName.contains("ekonomia"))
                || roleName.contains("pishtari")
                || roleName.contains("kf")) {
            Payment savedPayment = this.paymentRepository.save(payment);
            Optional<ApplicationUser> user = this.userService.findById(savedPayment.getApplicationUser().getId());
            savedPayment.setApplicationUser(user.orElse(null));
            emailService.sendPaymentEmailToClient(savedPayment);
            return savedPayment;
        }
        return null;
    }

    public Payment updatePayment(Payment payment) {
        ApplicationUser appUser = jwtUserDetailsService.getUserFromToken();
        String roleName = appUser.getRole().getName().toLowerCase();

        if ((roleName.contains("pg") && roleName.contains("ekonomia"))
                || roleName.contains("pishtari")
                || roleName.contains("kf")) {
            return this.paymentRepository.save(payment);
        }
        return null;
    }

    public void deletePayment(Long id) {
        ApplicationUser appUser = jwtUserDetailsService.getUserFromToken();
        String roleName = appUser.getRole().getName().toLowerCase();

        if ((roleName.contains("pg") && roleName.contains("ekonomia"))
                || roleName.contains("pishtari")
                || roleName.contains("kf")) {
            this.paymentRepository.deleteById(id);
        }
    }
}
