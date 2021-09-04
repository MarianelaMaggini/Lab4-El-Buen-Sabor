package com.example.buensabor.security.services;

import com.example.buensabor.security.dto.EmailValuesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@Service
public class EnviarMailService {
    private final JavaMailSender javaMailSender;
    private  final TemplateEngine templateEngine;

    @Value("${mail.urlFront}")
    private String urlFront;

    @Value("${spring.mail.username}")
    private String emailFrom;

    @Autowired
    public EnviarMailService(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    @Async
    public void sendEmail(String emailTo, String subject, String nombre, String token) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        Context context = new Context();
        Map<String, Object> model = new HashMap<>();
        model.put("nombre", nombre);
        model.put("url", urlFront + emailTo);
        context.setVariables(model);
        String htmlText = templateEngine.process("email-verification", context);
        helper.setFrom(emailFrom);
        helper.setSubject(subject);
        helper.setTo(emailTo);
        helper.setText(htmlText, true);
        javaMailSender.send(message);
    }
}
