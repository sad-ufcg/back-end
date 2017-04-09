package com.github.sadufcg.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.sadufcg.pojo.Question;
import com.github.sadufcg.repositories.QuestionRepository;

/**
 * This controller provides the public API that is used to perform operations
 * for {@link Question}.
 * 
 * @author Isabelly Cavalcante, Antunes Dantas
 */
@RestController
@RequestMapping("/question")
public class QuestionREST {

	private final QuestionRepository questionRepository;

	@Autowired
	QuestionREST(QuestionRepository questionService) {
		this.questionRepository = questionService;
	}

	/**
	 * Create a new question.
	 * 
	 * @param newQuestion
	 *            The information of the created question.
	 * @return The information of the created question.
	 */
	@CrossOrigin
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	Question create(@RequestBody @Valid Question newQuestion) {
		Question created = questionRepository.save(newQuestion);
		return created;
	}

	/**
	 * Finds a single question.
	 * 
	 * @param id
	 *            The id of the requested question.
	 * @return The information of the requested question.
	 */
	@CrossOrigin
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	Question findById(@PathVariable("id") Long id) {
		Question question = questionRepository.findOne(id);
		return question;
	}

	/**
	 * Finds all the question entries
	 * 
	 * @return A list with all the questions
	 */
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET)
	List<Question> findAll() {
		List<Question> questionEntries = questionRepository.findAll();
		return questionEntries;
	}

	/**
	 * Updates the information of an existing question.
	 * 
	 * @param updatedQuestion
	 *            The new information of the updated question.
	 * @return The updated information of the updated question.
	 */
	@CrossOrigin
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	Question update(@PathVariable("id") Long id, @RequestBody @Valid Question updatedQuestion) {
		if (id != updatedQuestion.getId()) {
			return null;
		} else {
			Question updated = questionRepository.save(updatedQuestion);
			return updated;
		}
	}

	/**
	 * Deletes a question entry.
	 * 
	 * @param id
	 *            of the question.
	 */
	@CrossOrigin
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	void delete(@PathVariable("id") Long id) {
		questionRepository.deleteBy(id);
	}
}
