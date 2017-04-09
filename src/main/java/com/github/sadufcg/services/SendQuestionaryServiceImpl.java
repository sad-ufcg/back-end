package com.github.sadufcg.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.sadufcg.pojo.Course;
import com.github.sadufcg.pojo.CourseStudent;
import com.github.sadufcg.repositories.CourseRepository;
import com.github.sadufcg.repositories.CourseStudentRepository;

/**
 * Created by antunessilva on 20/03/17.
 */

@Service
public class SendQuestionaryServiceImpl implements SendQuestionaryService {

    private final MailServerService mailService;
    private final CourseRepository courseService;
    private final CourseStudentRepository courseStudentRepository;

    @Autowired
    public SendQuestionaryServiceImpl(MailServerService mailServerService,
                                      CourseRepository courseService, CourseStudentRepository courseStudentRepository) {
        this.mailService = mailServerService;
        this.courseService = courseService;
        this.courseStudentRepository = courseStudentRepository;
    }

    public void sendQuestionnaire(Course c) {
        for (CourseStudent courseStudent : courseStudentRepository.findByCourse(c)) {
            String studentsEmail = courseStudent.getStudent().getEmail();
            String mailBody = generateMailBody(courseStudent);
            mailService.sendEmail(studentsEmail, mailBody);
        }
    }

    public void sendQuestionnaireForAllCourses() {
        List<Course> courses = courseService.findAll();
        for(Course c : courses) {
            sendQuestionnaire(c);
        }
    }

    private String generateMailBody(CourseStudent courseStudent) {
        return "teste teste teste";
    }

}
