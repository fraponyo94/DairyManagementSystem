package team.project.dairymanagementsystem.service;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;


    @Async
    public String sendEmail(String from, String to, String subject, Map<String, Object> variables, String emailTemplate) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

            messageHelper.setFrom(from);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);

            Context context = new Context();
            context.setVariables(variables);

            String contentTemplate = templateEngine.process(emailTemplate, context);

            messageHelper.setText(contentTemplate, true);
        };

        try {
            mailSender.send(messagePreparator);
            return "SUCCESS: ";
        }catch(MailException ex){
            System.out.println("Failed to send email with error: " + ex.getMessage());
            return "ERROR: ";
        }
    }


}

