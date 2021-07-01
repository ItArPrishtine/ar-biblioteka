package com.akropoliprishtine.services;

import com.sendgrid.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SendGridService {

    @Autowired
    private SendGrid sendGrid;

    private String EMAIL_TEMPLATE_ID = "d-1f5a4a3abb0a487680048a5d51cc386a";

    public String sendEmailWithSendGrid(String emailTo) {

        Email from = new Email("it.arprishtine@gmail.com");
        Email to = new Email(emailTo);

        Content content = new Content("text/html", "I'm replacing the <strong>body tag</strong>" + "mesazhi");

        Mail mail = new Mail(from, "Miresevini ne akropolin e ri prishtine", to, content);

//        mail.personalization.get(0).addSubstaitution("-username-", "Some blog user");
        mail.setTemplateId(EMAIL_TEMPLATE_ID);

        Request request = new Request();
        Response response = null;


        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            response = sendGrid.api(request);

            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return "email was successfully send";
    }
}