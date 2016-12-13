package com.github.sadufcg.services;

import java.util.List;

import com.github.sadufcg.pojo.Answer;

/**
 * This service provides operations for {@link Answer} objects.
 * 
 * @author Isabelly Cavalcante, Antunes Dantas
 */
public interface AnswerService {

	/**
	 * Creates a new answer entry.
	 * 
	 * @param newAnswer
	 *            New answer to create.
	 * @return The answer created.
	 */
	Answer create(Answer newAnswer);

	/**
	 * Finds a answer by using the id.
	 * 
	 * @param id
	 *            The id of the wanted answer.
	 * @return The information of the requested answer.
	 */
	Answer findById(Long id);
	
	/**
	 * Updates the information of an existing answer.
	 * 
	 * @param updatedAnswer
	 *            The new information of an existing answer.
	 * @return The information of the updated answer.
	 */
	Answer update(Answer updatedAnswer);
	
	/**
	 * Find all answers
	 * @return A list with all the answers
	 */
	List<Answer> findAll();
	
	/**
	 * Deletes an entrance on the database
	 * @param id
	 */
	void delete(Long id);
	
}
