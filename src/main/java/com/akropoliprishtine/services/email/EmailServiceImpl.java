package com.akropoliprishtine.services.email;

import com.akropoliprishtine.entities.ApplicationUser;
import com.sendinblue.Sendinblue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl {

    final String URL = "https://api.sendinblue.com/v3.0";
    final String API_KEY = "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";
    Sendinblue sendinblue;

    private void initConnection() {
        this.sendinblue = new Sendinblue(URL,API_KEY);
    }

    public void sendEmail(EmailModel emailModel) {
        this.initConnection();
        String agon = this.sendinblue.send_email(emailModel);
        System.out.println(agon);
    }

    public void accountCreated(ApplicationUser user) {
        EmailModel emailModel = new EmailModel();
        EmailModel.Sender sender = new EmailModel.Sender();
        EmailModel.To to = new EmailModel.To();

        sender.setEmail("it.arprishtine@gmail.com");
        sender.setName("It ArPrishtine");
        to.setName(user.getFirstName());
        to.setEmail(user.getEmail());

        emailModel.setTo(to);
        emailModel.setSender(sender);

        emailModel.setName("It ArPrishtine");
        emailModel.setSubject("Llogaria juaj eshte krijuar ne Akropolin e ri ne Prishtine");
        emailModel.setSubject("Llogaria eshte krijuar");
        emailModel.setHtmlContent("HTML Content");

        this.sendEmail(emailModel);
    }



//    String accountDetail = http.get_account();
//		System.out.println(accountDetail);
//		System.out.println(" ");
//
//    String smtpDetail = http.get_smtp_details();
//		System.out.println(smtpDetail);
//		System.out.println(" ");
}