package com.akropoliprishtine.services;

import com.sendgrid.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Service
public class SendGridService {

    @Autowired
    private SendGrid sendGrid;
    private final String CONFIG_VERSION = "2.3.23";
    private final Email from = new Email("it.arprishtine@gmail.com");

    public void sendEmailWithSendGrid(String subject, String emailTo, Map<String, Object> templateData, String templateUrl) {
        Email to = new Email(emailTo);

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

            Mail mail = new Mail(from, subject, to, content);

            Request request = new Request();

            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            sendGrid.api(request);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
