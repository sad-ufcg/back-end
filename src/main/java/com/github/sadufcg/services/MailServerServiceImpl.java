package com.github.sadufcg.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * Created by Antunes Dantas on 17/03/17.
 */
@Service
public class MailServerServiceImpl implements MailServerService {

    JavaMailSender mailSender;

    @Value("${mail.reply.adress}")
    private String replyMailAdress;

    @Value("${mail.subject}")
    private String subject;

    @Autowired
    public MailServerServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String recipient, String mailBody) throws MessagingException {
        MimeMessage mail = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail, true);
        helper.setTo(recipient);
        helper.setReplyTo(replyMailAdress);
        helper.setFrom(replyMailAdress);
        helper.setSubject(subject);
        helper.setText(mailBody);
        mailSender.send(mail);
    }

}
