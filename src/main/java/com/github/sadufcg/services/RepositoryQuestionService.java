package com.github.sadufcg.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.sadufcg.pojo.Question;
import com.github.sadufcg.repositories.QuestionRepository;

/**
 * Implements the interface {@link QuestionService}
 * 
 * @author Isabelly Cavalcante, Antunes Dantas
 */
@Service
final class RepositoryQuestionService implements QuestionService {

	private final QuestionRepository repository;

	@Autowired
	RepositoryQuestionService(QuestionRepository repository) {
		this.repository = repository;
	}

	@Transactional
	public Question create(Question newQuestionEntry) {
		Question created = repository.save(newQuestionEntry);
		return created;
	}

	@Transactional(readOnly = true)
	@Override
	public Question findById(Long id) {
		Question questionEntry = findQuestionEntryById(id);
		return questionEntry;
	}

	private Question findQuestionEntryById(Long id) {
		Question questionEntry = repository.findOne(id);
		return questionEntry;
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<Question> findAll() {
		List<Question> questions = repository.findAll();
		return questions;
	}
	
	@Transactional
	@Override
	public Question update(Question updatedQuestion) {
		Question updated = repository.save(updatedQuestion);
		return updated;
	}
	
	@Transactional
	public void delete(Long id) {
		Question question = findQuestionEntryById(id);
		repository.delete(question);
	}
}