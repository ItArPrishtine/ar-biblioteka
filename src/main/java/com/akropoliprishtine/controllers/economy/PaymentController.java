package com.akropoliprishtine.controllers.economy;

import com.akropoliprishtine.dto.BookBorrowDTO;
import com.akropoliprishtine.entities.book.Book;
import com.akropoliprishtine.entities.economy.Payment;
import com.akropoliprishtine.services.book.BookService;
import com.akropoliprishtine.services.economy.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/economy_payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/")
    public Payment createPayment(@RequestBody Payment payment) {
        Payment responsePayment = null;
        try {
            responsePayment = this.paymentService.createPayment(payment);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return responsePayment;
    }

    @GetMapping("/")
    public List<Payment> getPayments() {
        return this.paymentService.getPayments();
    }

    @PutMapping("/")
    public Payment updatePayment(@RequestBody Payment payment) {
        return this.paymentService.updatePayment(payment);
    }


    @PutMapping("/verify")
    public Payment verifyPayment(@RequestBody Long payment) {
        return this.paymentService.verifyPayment(payment);
    }

    @DeleteMapping("/{id}")
    public void deletePayment(@PathVariable Long id) {
        this.paymentService.deletePayment(id);
    }

    @GetMapping("/{id}")
    public Optional<Payment> getPaymentDetails(@PathVariable Long id) {
        return this.paymentService.getPaymentById(id);
    }
}

