package com.github.sadufcg.services;

import com.github.sadufcg.pojo.Course;
import com.github.sadufcg.pojo.CourseStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

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

    public void sendMailForOneCourse(String courseId, String subject, String mailBody) {
        Course course = courseService.findById(courseId);
        Set<CourseStudent> courseStudent = course.getCourseStudent();
        for (CourseStudent cs : courseStudent) {
            mailService.sendEmail(cs.getStudent().getEmail(), subject, mailBody);
        }
    }

}
