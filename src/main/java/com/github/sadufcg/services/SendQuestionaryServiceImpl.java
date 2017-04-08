package com.github.sadufcg.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.sadufcg.pojo.Course;
import com.github.sadufcg.pojo.CourseStudent;

/**
 * Created by antunessilva on 20/03/17.
 */

@Service
public class SendQuestionaryServiceImpl implements SendQuestionaryService {

    private final MailServerService mailService;
    private final CourseService courseService;
    private final AnswersListService answersListService;

    @Autowired
    public SendQuestionaryServiceImpl(MailServerService mailServerService,
                                      CourseService courseService,
                                      AnswersListService answersListService) {
        this.mailService = mailServerService;
        this.courseService = courseService;
        this.answersListService = answersListService;
    }

    public void sendQuestionnaire(String courseId) {
        Course course = courseService.findById(courseId);
        for (CourseStudent courseStudent : course.getCourseStudent()) {
            String studentsEmail = courseStudent.getStudent().getEmail();
            String mailBody = generateMailBody(courseStudent);

            mailService.sendEmail(studentsEmail, mailBody);
        }
    }

    public void sendQuestionnaireForAllCourses() {
        List<Course> courses = courseService.findAll();
        for(int i = 0; i < courses.size(); i++) {
            sendQuestionnaire(courses.get(i).getId());
        }
    }

    private String generateMailBody(CourseStudent courseStudent) {
        return "";
    }

}
