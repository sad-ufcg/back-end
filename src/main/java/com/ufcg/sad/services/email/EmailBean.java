package com.ufcg.sad.services.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * Serviço para enviar emails
 *
 * @author Antunes Dantas
 */
@Component
public class EmailBean implements EmailService{

    @Autowired
    private JavaMailSender emailServer;

    public void enviarEmail(String destinatario, String assunto, String corpo) {
        SimpleMailMessage email =  new SimpleMailMessage();
        email.setTo(destinatario);
        email.setSubject(assunto);
        email.setText(corpo);

        emailServer.send(email);
    }

}
