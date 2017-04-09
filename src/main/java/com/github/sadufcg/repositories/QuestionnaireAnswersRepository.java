package com.github.sadufcg.repositories;

import com.github.sadufcg.pojo.QuestionnaireAnswers;

public interface QuestionnaireAnswersRepository extends BaseRepository<QuestionnaireAnswers, Long> {

    QuestionnaireAnswers findOne(Long id);

	QuestionnaireAnswers save(QuestionnaireAnswers questionnaireAnswers);

}
