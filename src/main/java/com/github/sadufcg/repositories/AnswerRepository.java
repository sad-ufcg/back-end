package com.github.sadufcg.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.github.sadufcg.pojo.Answer;

/**
 * This repository provides operations for {@link Answer} objects.
 *
 * @author Isabelly Cavalcante, Antunes Dantas
 */
public interface AnswerRepository extends CrudRepository<Answer, Long> {
	
	Answer findOne(Long id);
	
	List<Answer> findAll();
}
