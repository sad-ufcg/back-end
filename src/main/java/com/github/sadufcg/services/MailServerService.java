package com.github.sadufcg.services;

import javax.mail.MessagingException;

/**
 * Created by Antunes Dantas on 17/03/17.
 */
public interface MailServerService {

   void sendEmail(String recipient, String mailBody) throws MessagingException;

}
