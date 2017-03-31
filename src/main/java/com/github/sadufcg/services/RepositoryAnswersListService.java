package com.github.sadufcg.services;

import com.github.sadufcg.pojo.AnswersList;
import com.github.sadufcg.repositories.AnswersListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Antunes Dantas on 31/03/17.
 */

@Service
public class RepositoryAnswersListService implements AnswersListService {

    @Autowired
    AnswersListRepository answersListRepository;

    public AnswersList save(AnswersList answersList) {
        return answersListRepository.save(answersList);
    }

    public AnswersList findOne(Long id) {
        return answersListRepository.findOne(id);
    }

}
