package com.github.sadufcg.repositories;

import java.util.List;

import com.github.sadufcg.pojo.Question;

/**
 * This repository provides operations for {@link Question} objects.
 *
 * @author Isabelly Cavalcante, Antunes Dantas
 */
public interface QuestionRepository extends BaseRepository<Question, Long> {

	Question findOne(Long id);

	Question save(Question question);
	
	List<Question> findAll();

	// Perguntar a Matheus pq n tem metodo para put no repository...
}
