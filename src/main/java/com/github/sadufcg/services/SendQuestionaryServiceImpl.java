package com.github.sadufcg.services;

import com.github.sadufcg.pojo.Course;
import com.github.sadufcg.pojo.CourseStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import java.util.Set;
=======
import java.util.List;
>>>>>>> 28c9c1c365bcf0b0cca2f51a4f4a34412440b241

/**
 * Created by antunessilva on 20/03/17.
 */

@Service
public class SendQuestionaryServiceImpl implements SendQuestionaryService {

    private final MailServerService mailService;
    private final CourseService courseService;

    @Autowired
    public SendQuestionaryServiceImpl(MailServerService mailServerService,
                                      CourseService courseService) {
        this.mailService = mailServerService;
        this.courseService = courseService;
    }

<<<<<<< HEAD
    public void sendMailForOneCourse(String courseId, String subject, String mailBody) {
        Course course = courseService.findById(courseId);
        Set<CourseStudent> courseStudent = course.getCourseStudent();
        for (CourseStudent cs : courseStudent) {
            mailService.sendEmail(cs.getStudent().getEmail(), subject, mailBody);
        }
    }
=======
    public void sendQuestionnaire(String courseId) {
        Course course = courseService.findById(courseId);
        for (CourseStudent courseStudent : course.getCourseStudent()) {
            String studentsEmail = courseStudent.getStudent().getEmail();
            String mailBody = generateMailBody(courseStudent);
            mailService.sendEmail(studentsEmail, mailBody);
        }
    }

    public void sendQuestionnaireForAllClasses() {
        List<Course> courses = courseService.findAll();
        for(int i = 0; i < courses.size(); i++) {
            sendQuestionnaire(courses.get(i).getId());
        }
    }

    private String generateMailBody(CourseStudent courseStudent) {
        return "";
    }
>>>>>>> 28c9c1c365bcf0b0cca2f51a4f4a34412440b241

}
