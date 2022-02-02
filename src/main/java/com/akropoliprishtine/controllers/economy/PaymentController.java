package com.akropoliprishtine.controllers.economy;

import com.akropoliprishtine.entities.economy.Payment;
import com.akropoliprishtine.services.economy.PaymentService;
import com.akropoliprishtine.services.economy.PdfGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/economy_payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create")
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

    @GetMapping("/read")
    public List<Payment> getPayments(@RequestParam(defaultValue = "0", required = false) int organization) {
        return this.paymentService.getPayments(organization);
    }

    @PutMapping("/update")
    public Payment updatePayment(@RequestBody Payment payment) {
        return this.paymentService.updatePayment(payment);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePayment(@PathVariable Long id) {
        this.paymentService.deletePayment(id);
    }

    @GetMapping("/read/{id}")
    public Optional<Payment> getPaymentDetails(@PathVariable Long id) {
        return this.paymentService.getPaymentById(id);
    }


    @RequestMapping(value = "/export/pdf", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> paymentsReport(
            @RequestParam(defaultValue = "0", required = false) int organization) {

        List<Payment> payments = paymentService.getPayments(organization);

        ByteArrayInputStream bis = PdfGenerator.paymentReports(payments);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=paymentReports.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

}

