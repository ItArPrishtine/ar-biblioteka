package com.akropoliprishtine.services;

import com.akropoliprishtine.dto.BorrowsEmailDTO;
import com.akropoliprishtine.entities.DailyJob;
import com.akropoliprishtine.entities.book.Borrow;
import com.akropoliprishtine.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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

        sendGridService.sendEmailWithSendGrid(subject, Collections.singletonList(email), templateData, templateUrl);
    }

    public void sendBorrowEmailToLibrary(List<String> emails, Borrow borrow) {
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


        sendGridService.sendEmailWithSendGrid(subject, emails, templateData, templateUrl);
    }

    public void sendEmailForLateBorrows(List<String> emails, List<BorrowsEmailDTO> borrows) {
        final String subject = "Lista e librave te vonuar";
        final String templateUrl = "templates/mail/lateBorrows.ftl";

        Map<String, Object> templateData = new HashMap<>();
        templateData.put("borrows", borrows);
        sendGridService.sendEmailWithSendGrid(subject, emails, templateData, templateUrl);
    }

    public void sendEmailToPostJobs(List<DailyJob> jobs) {
        final String subject = "Pozita te punes per t'u postuar sot";
        final String templateUrl = "templates/mail/jobsToPost.ftl";

        Map<String, Object> templateData = new HashMap<>();
        templateData.put("jobs", jobs);

        List<String> receivers = new ArrayList<>();
        receivers.add("agonhaxhani83@gmail.com");
        receivers.add("greseveseli@gmail.com");
        receivers.add("kreshnikqorraj@gmail.com");

        sendGridService.sendEmailWithSendGrid(subject, receivers, templateData, templateUrl);
    }

    public void sendEmailForJobsPersonally(List<DailyJob> jobs, String userEmail) {
        final String subject = "Pozita te punes per ju";
        final String templateUrl = "templates/mail/jobsToPostPerUser.ftl";

        Map<String, Object> templateData = new HashMap<>();
        templateData.put("jobs", jobs);

        List<String> receivers = new ArrayList<>();
        receivers.add(userEmail);

        sendGridService.sendEmailWithSendGrid(subject, receivers, templateData, templateUrl);
    }
}
