package com.github.sadufcg.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        

}
