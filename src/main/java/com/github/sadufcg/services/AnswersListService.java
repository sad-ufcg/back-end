package com.github.sadufcg.services;

import com.github.sadufcg.pojo.AnswersList;

public interface AnswersListService {

    public AnswersList save(AnswersList answersList);

    public AnswersList findOne(Long id);

}
