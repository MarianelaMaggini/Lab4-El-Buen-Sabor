package com.example.buensabor.services.mail;

import com.example.buensabor.entities.comprobantes.Factura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class FacturaMailService {

    @Autowired
    JavaMailSender javaMailSender;

    @Async
    public void sendEmail(String from, String to, String subject, String msg, Factura factura) throws MessagingException {
        try{
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(msg);
            // attach the file
            String fileName = factura.getId() + "_factura.pdf";
            FileSystemResource file = new FileSystemResource(new File("src//main//resources//files//" + fileName));
            helper.addAttachment(fileName, file);//image will be sent by this name
            javaMailSender.send(message);
        }
        catch(MessagingException e) {
            e.printStackTrace();
        }
    }

}
