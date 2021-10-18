package com.example.buensabor.security.services;

import com.example.buensabor.security.dto.EmailValuesDto;
import org.springframework.beans.factory.annotation.Autowired;

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

    @Autowired
    public EnviarMailService(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    @Async
    public void sendEmail(EmailValuesDto dto, String template, String url) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message);
            Context context = new Context();
            Map<String, Object> model = new HashMap<>();
            model.put("nombre", dto.getNombre());
            model.put("url", url + dto.getTokenPassword());
            context.setVariables(model);
            String htmlText = templateEngine.process(template, context);
            helper.setFrom(dto.getMailFrom());
            helper.setSubject(dto.getSubject());
            helper.setTo(dto.getMailTo());
            helper.setText(htmlText, true);
            javaMailSender.send(message);
        }catch (MessagingException ex){
            ex.printStackTrace();
        }
    }

}
