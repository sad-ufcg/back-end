package com.github.sadufcg.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.sadufcg.pojo.Answer;
import com.github.sadufcg.repositories.AnswerRepository;

/**
 * Implements the interface {@link AnswerService}
 *  
 * @author Isabelly Cavalcante, Antunes Dantas
 */
@Service
final class RepositoryAnswerService implements AnswerService {
	
    private final AnswerRepository repository;

    @Autowired
    RepositoryAnswerService(AnswerRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Answer create(Answer newAnswerEntry) {
       Answer created = repository.save(newAnswerEntry);
        return created;
    }

    @Transactional(readOnly = true)
    @Override
    public Answer findById(Long id) {
        Answer todoEntry = findAnswerEntryById(id);
        return todoEntry;
    }

    private Answer findAnswerEntryById(Long id) {
    	Answer answerEntry = repository.findOne(id);
        return answerEntry;
    }
    
    @Transactional(readOnly = true)
    @Override
    public List<Answer> findAll() {
    	List<Answer> answers = repository.findAll();
    	return answers;
    }
    
    @Transactional
    @Override
    public Answer update(Answer updatedAnswer) {
        Answer updated = repository.save(updatedAnswer);
        return updated;
    }
    
    @Transactional
    public void delete(Long id) {
    	Answer answer = findAnswerEntryById(id);
    	repository.delete(answer);
    }
}
