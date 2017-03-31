package com.github.sadufcg.repositories;

import com.github.sadufcg.pojo.AnswersList;

/**
 * @author Antunes Dantas on 31/03/17.
 */
public interface AnswersListRepository extends BaseRepository<AnswersList, Long> {

    AnswersList findOne(Long id);

    AnswersList save(AnswersList answersList);

}
