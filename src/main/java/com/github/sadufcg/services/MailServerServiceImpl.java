package com.github.sadufcg.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by Antunes Dantas on 17/03/17.
 */
public class MailServerServiceImpl implements MailServerService {

    JavaMailSender mailSender;
    private final String REPLYMAILADRESS = "put here the reply mail";

    @Autowired
    public MailServerServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String recipient, String subject, String mailBody) {
        MimeMessage mail = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(recipient);
            helper.setReplyTo(REPLYMAILADRESS);
            helper.setFrom(REPLYMAILADRESS);
            helper.setSubject(subject);
            helper.setText(mailBody);
        } catch (MessagingException e) {
            e.printStackTrace();
        } finally {}
        mailSender.send(mail);
    }

}
