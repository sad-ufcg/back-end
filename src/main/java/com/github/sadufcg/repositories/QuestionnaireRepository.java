package com.github.sadufcg.repositories;

import com.github.sadufcg.pojo.Questionnaire;

import java.util.List;

/**
 * @author Antunes Dantas on 31/03/17.
 */
public interface QuestionnaireRepository extends BaseRepository<Questionnaire, Long> {

    Questionnaire findOne(Long id);

    Questionnaire save(Questionnaire questionnaire);

    List<Questionnaire> findAll();

    void delete(Questionnaire questionnaire);

}
