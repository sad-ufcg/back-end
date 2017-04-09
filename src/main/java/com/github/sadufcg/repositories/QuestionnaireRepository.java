package com.github.sadufcg.repositories;

import java.util.List;

import com.github.sadufcg.pojo.Questionnaire;

/**
 * @author Antunes Dantas on 31/03/17.
 */
public interface QuestionnaireRepository extends BaseRepository<Questionnaire, Long> {

    Questionnaire findOne(Long id);

    Questionnaire save(Questionnaire questionnaire);

    List<Questionnaire> findAll();

    void delete(Questionnaire questionnaire);

	void deleteBy(Long id);

}
