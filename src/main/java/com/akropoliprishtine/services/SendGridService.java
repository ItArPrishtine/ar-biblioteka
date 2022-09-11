package com.akropoliprishtine.services;

import com.sendgrid.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class SendGridService {

    @Autowired
    private SendGrid sendGrid;
    private final String CONFIG_VERSION = "2.3.23";
    private final Email from = new Email("it.arprishtine@gmail.com");

    public void sendEmailWithSendGrid(String subject, List<String> emailTo , Map<String, Object> templateData, String templateUrl) {
        try {
            Configuration cfg = new Configuration(new Version(CONFIG_VERSION));

            cfg.setClassForTemplateLoading(SendGridService.class, "/");
            cfg.setDefaultEncoding("UTF-8");

            Template template = cfg.getTemplate(templateUrl);

            String templateContent = null;

            try (StringWriter out = new StringWriter()) {
                template.process(templateData, out);
                templateContent = out.getBuffer().toString();
                out.flush();
            }

            Content content = new Content("text/html", templateContent);

            Mail mail = new Mail();

            mail.setFrom(from);
            mail.setSubject(subject);
            Personalization personalization = new Personalization();

            emailTo.forEach(item -> {
                Email to = new Email(item);
                personalization.addTo(to);
            });
            mail.addPersonalization(personalization);
            mail.addContent(content);

            Request request = new Request();

            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            log.info("Email sent");

            sendGrid.api(request);
        } catch (Exception ex) {
            log.error("Email failed to be sent", ex);
            ex.printStackTrace();
        }
    }
}
