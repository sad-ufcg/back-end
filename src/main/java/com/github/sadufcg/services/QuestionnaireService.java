package com.github.sadufcg.services;

import com.github.sadufcg.pojo.Questionnaire;

import java.util.List;

/**
 * Created by epol-antunes on 31/03/17.
 */
public interface QuestionnaireService {

    Questionnaire create(Questionnaire questionnaire);

    Questionnaire findById(Long id);

    Questionnaire update(Questionnaire questionnaire);

    List<Questionnaire> findAll();

    void delete(Long id);

}
