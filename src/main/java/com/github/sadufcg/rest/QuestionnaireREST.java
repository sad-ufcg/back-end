package com.github.sadufcg.rest;

import com.github.sadufcg.pojo.Questionnaire;
import com.github.sadufcg.services.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Antunes Dantas on 31/03/17.
 */

@RestController
@RequestMapping(value = "/questionnaire")
@CrossOrigin
public class QuestionnaireREST {

    @Autowired
    QuestionnaireService questionnaireService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Questionnaire getById(@RequestParam("id") Long id) {
        return questionnaireService.findById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Questionnaire> findAll() {
        return questionnaireService.findAll();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<Questionnaire> update(@RequestParam("id") Long id, @RequestBody Questionnaire questionnaire) {
        if (id == questionnaire.getId()) {
            return new ResponseEntity<Questionnaire>(questionnaireService.update(questionnaire), HttpStatus.OK);
        } else {
            return new ResponseEntity<Questionnaire>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Questionnaire create(@RequestBody Questionnaire questionnaire) {
        return questionnaireService.create(questionnaire);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@RequestParam("id") Long id) {
        Questionnaire questionnaire = questionnaireService.findById(id);
        if (questionnaire != null) {
            questionnaireService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}
