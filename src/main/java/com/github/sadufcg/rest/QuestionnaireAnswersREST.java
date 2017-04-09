package com.github.sadufcg.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.sadufcg.pojo.Answer;
import com.github.sadufcg.pojo.QuestionnaireAnswers;
import com.github.sadufcg.pojo.Token;
import com.github.sadufcg.repositories.QuestionRepository;
import com.github.sadufcg.repositories.QuestionnaireAnswersRepository;
import com.github.sadufcg.repositories.TokenRepository;

@RestController
@RequestMapping(value = "/questionnaireanswers")
@CrossOrigin
public class QuestionnaireAnswersREST {

    @Autowired
    QuestionnaireAnswersRepository questionnaireRepository;

    @Autowired
    QuestionRepository questionRepository;
    
    @Autowired
    TokenRepository tokenRepository;
    
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public QuestionnaireAnswers create(@RequestBody QuestionnaireAnswers questionnaire) {
    	Token token = tokenRepository.findOne(questionnaire.getToken().getId());
    	token = tokenRepository.findAll().get(0); // TODO XXX REMOVE TEST!
    	for (Answer a : questionnaire.getAnswers()) {
    		a.setQuestion(questionRepository.findOne(a.getQuestion().getId()));
    	}
    	if (token == null) {
    		// TODO return correct status code
    		return null;
    	}
		if (questionnaire.isInvalid()) {
			 // TODO invalid
			return null;
		}
		questionnaire.setId(null);
		questionnaire.setCourse(token.getCourse());
		QuestionnaireAnswers questionnaireAnswers = questionnaireRepository.save(questionnaire);
		tokenRepository.delete(token);
		return questionnaireAnswers;    			
    }

}