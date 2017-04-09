package com.github.sadufcg.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.sadufcg.pojo.QuestionnaireAnswers;
import com.github.sadufcg.repositories.QuestionnaireAnswersRepository;
import com.github.sadufcg.repositories.TokenRepository;

@RestController
@RequestMapping(value = "/questionnaireanswers")
@CrossOrigin
public class QuestionnaireAnswersREST {

    @Autowired
    QuestionnaireAnswersRepository questionnaireRepository;

    @Autowired
    TokenRepository tokenRepository;
    
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public QuestionnaireAnswers create(@RequestBody QuestionnaireAnswers questionnaire) {
        return questionnaireRepository.save(questionnaire);
    }

}