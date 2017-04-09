package com.github.sadufcg.services;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.sadufcg.pojo.Course;
import com.github.sadufcg.pojo.CourseStudent;
import com.github.sadufcg.pojo.Token;
import com.github.sadufcg.repositories.CourseRepository;
import com.github.sadufcg.repositories.CourseStudentRepository;
import com.github.sadufcg.repositories.TokenRepository;

/**
 * Created by antunessilva on 20/03/17.
 */

@Service
public class SendQuestionaryServiceImpl implements SendQuestionaryService {

    @Autowired
    private MailServerService mailService;
    
    @Autowired
    private CourseRepository courseService;
    
    @Autowired
    private CourseStudentRepository courseStudentRepository;

    @Autowired
    TokenRepository tokenRepository;
    
    public void sendQuestionnaire(Course c) {
        for (CourseStudent courseStudent : courseStudentRepository.findByCourse(c)) {
        	Token token = new Token(courseStudent.getCourse());
            String studentsEmail = courseStudent.getStudent().getEmail();
            String mailBody = generateMailBody(courseStudent);
            try {
				mailService.sendEmail(studentsEmail, mailBody);
				tokenRepository.save(token);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }

    public void sendQuestionnaireForAllCourses() {
        List<Course> courses = courseService.findAll();
        for(Course c : courses) {
            sendQuestionnaire(c);
        }
    }

    private String generateMailBody(CourseStudent courseStudent) {
        return courseStudent.toString();
    }

}
