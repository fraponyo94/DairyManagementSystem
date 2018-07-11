package team.project.dairymanagementsystem.controller;

import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import team.project.dairymanagementsystem.service.EmailService;

import java.util.HashMap;
import java.util.Map;

@Controller
public class SimpleEmailController {
    @Autowired
    private EmailService emailService;

    @RequestMapping("/sendemail")
    void home() {
        Map<String,Object> variable = new HashMap<>();
        variable.put("name","Moses Milimo");
        emailService.sendEmail("mozdemilly@gmail.com","ricdechox@gmail.com","Hello word trial",variable,"emailtrial");
       System.out.println("Email Successfully Send");
    }
}