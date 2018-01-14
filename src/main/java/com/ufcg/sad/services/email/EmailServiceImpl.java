package com.ufcg.sad.services.email;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    JavaMailSender mailSender;

    Logger logger = Logger.getLogger(EmailServiceImpl.class);

    @Value("${mail.reply.adress}")
    private String replyMailAdress;

    @Value("${mail.subject}")
    private String subject;

    @Override
    public void enviarEmail(String destinatario, String corpo) {
        try {
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(destinatario);
            helper.setReplyTo(replyMailAdress);
            helper.setFrom(replyMailAdress);
            helper.setSubject(subject);
            helper.setText(corpo);
            mailSender.send(mail);
        } catch (MessagingException e) {
            logger.warn("Não foi possível enviar email para " + destinatario);
        }
    }
}
