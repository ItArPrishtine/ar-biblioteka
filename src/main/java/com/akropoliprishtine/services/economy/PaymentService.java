package com.akropoliprishtine.services.economy;


import com.akropoliprishtine.entities.ApplicationUser;
import com.akropoliprishtine.entities.Organization;
import com.akropoliprishtine.entities.economy.Payment;
import com.akropoliprishtine.enums.UserRolesEnum;
import com.akropoliprishtine.repositories.UserRepository;
import com.akropoliprishtine.repositories.economy.PaymentRepository;
import com.akropoliprishtine.services.ApplicationUserService;
import com.akropoliprishtine.services.EmailService;
import com.akropoliprishtine.services.JwtUserDetailsService;
import com.akropoliprishtine.services.RoleService;
import com.akropoliprishtine.services.book.OrganizationService;
import com.akropoliprishtine.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleService roleService;

    @Autowired
    JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    ApplicationUserService userService;

    @Autowired
    EmailService emailService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;
    
    @Autowired
    OrganizationService organizationService;

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

    public List<Payment> getPayments(long organization) {
        ApplicationUser loggedUser = userService.getLoggedUser();
        
        Organization org;

        if (((loggedUser.getRole().getName().equals(UserRolesEnum.KK.label) || loggedUser.getRole().getName().equals(UserRolesEnum.ADMIN.label)) &&
                organization != 0)
        ) {
            org = organizationService.getOrganizationById(organization);
        } else {
            org = loggedUser.getOrganization();
        }


        return this.paymentRepository.findAllByOrganization(org);
    }

    public Optional<Payment> getPaymentById(Long id) {
        return this.paymentRepository.findById(id);
    }

    @Transactional
    public Payment createPayment(Payment payment) {
        ApplicationUser loggedUser = this.userService.getLoggedUser();
        payment.setOrganization(loggedUser.getOrganization());

        Payment savedPayment = this.paymentRepository.save(payment);
        Optional<ApplicationUser> user = this.userService.findById(savedPayment.getApplicationUser().getId());

        savedPayment.setApplicationUser(user.orElse(null));

        return savedPayment;
    }

    public Payment updatePayment(Payment payment) {
        return this.paymentRepository.save(payment);
    }

    public void deletePayment(Long id) {
        this.paymentRepository.deleteById(id);
    }
}
