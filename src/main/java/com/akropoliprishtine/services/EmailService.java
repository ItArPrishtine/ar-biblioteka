package com.akropoliprishtine.services;

import com.akropoliprishtine.dto.BorrowsEmailDTO;
import com.akropoliprishtine.entities.ApplicationUser;
import com.akropoliprishtine.entities.book.Borrow;
import com.akropoliprishtine.entities.economy.Payment;
import com.akropoliprishtine.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmailService {
    @Autowired
    SendGridService sendGridService;

    @Value("${links.book_details}")
    private String bookDetailsUrl;

    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public EmailService(SendGridService sendGridService,
                        JwtTokenUtil jwtTokenUtil) {
        this.sendGridService = sendGridService;
    }

    public void sendWelcomeMail(String email, String username) {
        final String subject = "Important: Miresevini ne arsekretarite.com";
        final String templateUrl = "templates/mail/welcomeuser.ftl";

        Map<String, Object> templateData = new HashMap<>();
        templateData.put("username", username);

        sendGridService.sendEmailWithSendGrid(subject, email, templateData, templateUrl);
    }

    public void sendBorrowEmailToLibrary(String email, Borrow borrow) {
        final String subject = "Nje liber i ri huazimi";
        final String templateUrl = "templates/mail/newBorrowLibrary.ftl";

        Map<String, Object> templateData = new HashMap<>();
        templateData.put("borrowerName", borrow.getApplicationUser().getFirstName() + " "
                + borrow.getApplicationUser().getFirstName());

        templateData.put("authorName", borrow.getBook().getAuthor().getFirstName() + " " +
                borrow.getBook().getAuthor().getLastName());

        templateData.put("bookTitle", borrow.getBook().getName());
        templateData.put("category", borrow.getBook().getCategory());
        templateData.put("borrowDate", dateFormat.format(borrow.getBorrowFrom()));
        templateData.put("dateToReturn", dateFormat.format(borrow.getBorrowUntil()));
        templateData.put("bookLink", bookDetailsUrl + borrow.getBook().getId());


        sendGridService.sendEmailWithSendGrid(subject, email, templateData, templateUrl);
    }

    public void sendBorrowEmailToClient(Borrow borrow) {
        final String subject = "Ju sapo huazuat nje liber te ri";
        final String templateUrl = "templates/mail/newBorrowClient.ftl";

        Map<String, Object> templateData = new HashMap<>();
        templateData.put("username", borrow.getApplicationUser().getFirstName());

        templateData.put("authorName", borrow.getBook().getAuthor().getFirstName() + " " +
                borrow.getBook().getAuthor().getLastName());

        templateData.put("bookTitle", borrow.getBook().getName());
        templateData.put("category", borrow.getBook().getCategory());
        templateData.put("borrowDate", dateFormat.format(borrow.getBorrowFrom()));
        templateData.put("dateToReturn", dateFormat.format(borrow.getBorrowUntil()));

        sendGridService.sendEmailWithSendGrid(subject, borrow.getApplicationUser().getEmail(), templateData, templateUrl);
    }

    public void sendEmailForBorrowDeadline(Borrow borrow, long daysLeft) {
        final String subject = "Deadline-i per kthimin e librit";
        final String templateUrl = "templates/mail/deadline-reminders.ftl";

        Map<String, Object> templateData = new HashMap<>();
        templateData.put("username", borrow.getApplicationUser().getFirstName());
        templateData.put("bookName", borrow.getBook().getName());
        templateData.put("days", daysLeft);
        templateData.put("url", "https://arsekretarite.com/account/biblioteka/extend-deadline/" + borrow.getId());
        
        // TODO GRUPOJ NE BAZE TE FILIALIT
        sendGridService.sendEmailWithSendGrid(subject, borrow.getApplicationUser().getEmail(), templateData, templateUrl);
        sendGridService.sendEmailWithSendGrid(subject, "agonhaxhani83@gmail.com", templateData, templateUrl);
    }

    public void sendEmailForLateBorrows(String email, List<BorrowsEmailDTO> borrows) {
        final String subject = "Lista e librave te vonuar";
        final String templateUrl = "templates/mail/lateBorrows.ftl";

        Map<String, Object> templateData = new HashMap<>();
        templateData.put("borrows", borrows);
        sendGridService.sendEmailWithSendGrid(subject, email, templateData, templateUrl);
    }
}
