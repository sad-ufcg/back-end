package com.github.sadufcg.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.sadufcg.pojo.Questionnaire;
import com.github.sadufcg.repositories.CourseRepository;
import com.github.sadufcg.services.SendQuestionaryService;

@RestController
@RequestMapping(value = "/mailman")
@CrossOrigin
public class MailmanREST {

    @Autowired
    SendQuestionaryService sendQuestionaryService;
    
    @Autowired
    CourseRepository courseRepository;
    
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Questionnaire questionnaire) {
    	sendQuestionaryService.sendQuestionnaireForAllCourses();
        //return questionnaireRepository.save(questionnaire);
    }

    @RequestMapping(value = "{courseId}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@PathVariable("courseId") Long courseId, @RequestBody Questionnaire questionnaire) {
    	sendQuestionaryService.sendQuestionnaire(courseRepository.findOne(courseId));
    }

    
}
