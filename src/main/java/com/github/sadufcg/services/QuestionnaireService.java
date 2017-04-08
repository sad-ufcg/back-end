package com.github.sadufcg.services;

import java.util.List;

import com.github.sadufcg.pojo.Questionnaire;

public interface QuestionnaireService {

    Questionnaire create(Questionnaire questionnaire);

    Questionnaire findById(Long id);

    Questionnaire update(Questionnaire questionnaire);

    List<Questionnaire> findAll();

    void delete(Long id);

}
