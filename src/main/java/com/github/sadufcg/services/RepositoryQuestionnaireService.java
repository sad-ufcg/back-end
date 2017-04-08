package com.github.sadufcg.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.sadufcg.pojo.Questionnaire;
import com.github.sadufcg.repositories.QuestionnaireRepository;

/**
 * @author Antunes Dantas on 31/03/17.
 */

@Service
public class RepositoryQuestionnaireService implements QuestionnaireService{

    @Autowired
    private QuestionnaireRepository questionnaireRepository;

    public Questionnaire create(Questionnaire questionnaire) {
        return questionnaireRepository.save(questionnaire);
    }

    public Questionnaire update(Questionnaire questionnaire) {
        return questionnaireRepository.save(questionnaire);
    }

    public Questionnaire findById(Long id) {
        return questionnaireRepository.findOne(id);
    }

    public List<Questionnaire> findAll() {
        return questionnaireRepository.findAll();
    }

    public void delete(Long id) {
        Questionnaire questionnaire = questionnaireRepository.findOne(id);
        if (questionnaire != null) {
            questionnaireRepository.delete(questionnaire);
        }
    }

}
