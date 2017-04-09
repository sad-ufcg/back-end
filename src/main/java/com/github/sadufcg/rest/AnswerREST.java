package com.github.sadufcg.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.github.sadufcg.pojo.Answer;
import com.github.sadufcg.repositories.AnswerRepository;

/**
 * This controller provides the public API that is used to perform operations
 * for {@link Answer}.
 * 
 * @author Isabelly Cavalcante, Antunes Dantas
 */
//@RestController
//@RequestMapping("/answer")
public class AnswerREST {

	private final AnswerRepository answerRepository;

	@Autowired
	AnswerREST(AnswerRepository answerService) {
		this.answerRepository = answerService;
	}

	/**
	 * Create a new answer.
	 * 
	 * @param newAnswer
	 *            The information of the created answer.
	 * @return The information of the created answer.
	 */
	@CrossOrigin
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	Answer create(@RequestBody @Valid Answer newAnswer) {
		Answer created = answerRepository.save(newAnswer);
		return created;
	}

	/**
	 * Finds a single answer.
	 * 
	 * @param id
	 *            The id of the requested answer.
	 * @return The information of the requested answer.
	 */
	@CrossOrigin
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	Answer findById(@PathVariable("id") Long id) {
		Answer answerEntry = answerRepository.findOne(id);
		return answerEntry;
	}

	/**
	 * Updates the information of an existing answer.
	 * 
	 * @param updatedAnswer
	 *            The new information of the updated answer.
	 * @return The updated information of the updated answer.
	 */
	@CrossOrigin
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	Answer update(@PathVariable("id") Long id, @RequestBody @Valid Answer updatedAnswer) {
		if (id != updatedAnswer.getId()) {
			return null;
		} else {
			Answer updated = answerRepository.save(updatedAnswer);
			return updated;
		}
	}

	/**
	 * Deletes an answer entry from the database
	 * 
	 * @param id
	 *            of the answer which will be deleted
	 */
	@CrossOrigin
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	void delete(@PathVariable("id") Long id) {
		answerRepository.delete(id);
	}
}
