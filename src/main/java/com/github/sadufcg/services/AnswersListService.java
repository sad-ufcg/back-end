package com.github.sadufcg.services;

import com.github.sadufcg.pojo.AnswersList;

/**
 * Created by epol-antunes on 31/03/17.
 */
public interface AnswersListService {

    public AnswersList save(AnswersList answersList);

    public AnswersList findOne(Long id);

}
