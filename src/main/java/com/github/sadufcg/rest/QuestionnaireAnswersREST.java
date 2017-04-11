package com.github.sadufcg.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    
    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, produces = "text/plain; charset=utf-8")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> create(@RequestBody QuestionnaireAnswers questionnaire) {
    	Token token = tokenRepository.findOne(questionnaire.getToken().getId());
    	// token = tokenRepository.findAll().get(0);
    	for (Answer a : questionnaire.getAnswers()) {
    		a.setQuestion(questionRepository.findOne(a.getQuestion().getId()));
    	}
    	if (token == null) {
    		return new ResponseEntity<>("Token inválido", HttpStatus.UNAUTHORIZED);
    	}
		if (questionnaire.isInvalid()) {
			tokenRepository.delete(token);
			System.err.println(">>> TOKEN REMOVIDO: " + token + " " + token.getCourse());
			return new ResponseEntity<>("Token removido", HttpStatus.OK);
		}
		questionnaire.setId(null);
		questionnaire.setCourse(token.getCourse());
		questionnaireRepository.save(questionnaire);
		tokenRepository.delete(token);
		return new ResponseEntity<>("Resposta registrada, obrigado!", HttpStatus.CREATED);    			
    }

}