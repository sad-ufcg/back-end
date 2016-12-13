package com.github.sadufcg.services;

import java.util.List;

import com.github.sadufcg.pojo.Question;

/**
 * This service provides operations for {@link Question} objects.
 * 
 * @author Isabelly Cavalcante, Antunes Dantas
 */
public interface QuestionService {

	/**
	 * Creates a new question entry.
	 * 
	 * @param newQuestion
	 *            The information of a new question to create.
	 * @return The question created.
	 */
	Question create(Question newQuestion);

	/**
	 * Finds a question by using the id.
	 * 
	 * @param id
	 *            The id of the wanted question.
	 * @return The information of the requested question.
	 */
	Question findById(Long id);

	/**
	 * Updates the information of an existing information.
	 * 
	 * @param updatedQuestion
	 *            The new information of an existing question.
	 * @return The information of the updated question.
	 */
	Question update(Question updatedQuestion);
	
	/**
	 * Find all questions
	 * @return A list with all the questions
	 */
	List<Question> findAll();
	
	/**
	 * Deletes a question entry from the database.
	 * 
	 * @param id of the question which will be deleted
	 */
	void delete(Long id);
}
