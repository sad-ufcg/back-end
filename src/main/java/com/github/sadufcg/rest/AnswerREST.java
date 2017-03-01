package com.github.sadufcg.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.github.sadufcg.pojo.Answer;
import com.github.sadufcg.services.AnswerService;

/**
 * This controller provides the public API that is used to perform operations
 * for {@link Answer}.
 * 
 * @author Isabelly Cavalcante, Antunes Dantas
 */
@RestController
@RequestMapping("/answer")
public class AnswerREST {

	private final AnswerService answerService;

	@Autowired
	AnswerREST(AnswerService answerService) {
		this.answerService = answerService;
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
		Answer created = answerService.create(newAnswer);
		return created;
	}

	/**
	 * Finds all the answer entries
	 * 
	 * @return A list with all the answers
	 */
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET)
	List<Answer> findAll() {
		List<Answer> answerEntries = answerService.findAll();
		return answerEntries;
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
		Answer answerEntry = answerService.findById(id);
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
			Answer updated = answerService.update(updatedAnswer);
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
		answerService.delete(id);
	}
}
