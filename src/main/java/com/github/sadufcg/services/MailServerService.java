package com.github.sadufcg.services;

import com.github.sadufcg.pojo.Course;

import java.util.List;

/**
 * Created by Antunes Dantas on 17/03/17.
 */
public interface MailServerService {

   void sendEmail(String recipient, String subject, String mailBody);

}
