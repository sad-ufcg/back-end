package com.github.sadufcg.services;

/**
 * Created by Antunes Dantas on 17/03/17.
 */
public interface MailServerService {

   void sendEmail(String recipient, String mailBody);

}
