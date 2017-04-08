package com.github.sadufcg.services;

import com.github.sadufcg.pojo.Questionnaire;

import java.util.List;

public interface QuestionnaireService {

    Questionnaire create(Questionnaire questionnaire);

    Questionnaire findById(Long id);

    Questionnaire update(Questionnaire questionnaire);

    List<Questionnaire> findAll();

    void delete(Long id);

}
