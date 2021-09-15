package com.example.buensabor.security.services;

import com.example.buensabor.security.dto.EmailValuesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.file.FileSystem;
import java.util.HashMap;
import java.util.Map;

@Service
public class EnviarMailService {
    private final JavaMailSender javaMailSender;
    private  final TemplateEngine templateEngine;

    @Value("${mail.urlFront}")
    private String urlFront;

    @Autowired
    public EnviarMailService(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    @Async
    public void sendEmail(EmailValuesDto dto) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message);
            Context context = new Context();
            Map<String, Object> model = new HashMap<>();
            model.put("nombre", dto.getNombre());
            model.put("url", urlFront + dto.getTokenPassword());
            context.setVariables(model);
            String htmlText = templateEngine.process("email-verification", context);
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
