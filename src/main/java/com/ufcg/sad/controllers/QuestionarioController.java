package com.ufcg.sad.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.sad.exceptions.questionario.QuestaoInvalidaException;
import com.ufcg.sad.exceptions.questionario.QuestionarioNaoExisteException;
import com.ufcg.sad.exceptions.questionario.QuestionarioSemNomeException;
import com.ufcg.sad.exceptions.questionario.QuestionarioVazioException;
import com.ufcg.sad.exceptions.utils.ParametroInvalidoException;
import com.ufcg.sad.models.questionario.Questionario;
import com.ufcg.sad.services.questionario.QuestionarioService;

/**
 * Este controller provê uma API para acessar questionários.
 * 
 * @author Marianne Linhares
 */
@RestController
@RequestMapping("/questionarios")
public class QuestionarioController {

	@Autowired
	private QuestionarioService questionarioService;
	
	/**
	 * Método para recuperar um questionário a partir de um certo id.
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Questionario> getQuestionario(@PathVariable("id") Long id) {
		try {
			Questionario questionario = questionarioService.getQuestionario(id);
			return new ResponseEntity<Questionario>(questionario, HttpStatus.OK);
		} catch (QuestionarioNaoExisteException e) {
			return new ResponseEntity<Questionario>(HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * Método para atualizar um questionário a partir de um certo id.
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Questionario> atualizaQuestionario(@PathVariable("id") Long id,
															 @RequestBody Questionario questionario) {
		try {
			questionarioService.atualizaQuestionario(id, questionario);
			return new ResponseEntity<Questionario>(HttpStatus.OK);
		} catch (QuestionarioNaoExisteException e) {
			return new ResponseEntity<Questionario>(HttpStatus.NOT_FOUND);
		} catch (QuestaoInvalidaException e) {
			return new ResponseEntity<Questionario>(HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Método para recuperar todos os questionários.
	 */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Questionario> > getTodosQuestionarios() {
        List<Questionario> questionarios = questionarioService.getTodosQuestionarios();
        return new ResponseEntity<List<Questionario> >(questionarios, HttpStatus.OK);
    }

    /**
	 * Método para criar um questionário.
	 * @param questionario
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Questionario> criaQuestionario(@RequestBody Questionario questionario) {
	    try {
	    	questionarioService.criaQuestionario(questionario);
	    } catch (QuestionarioVazioException e) {
	    	return new ResponseEntity<Questionario>(HttpStatus.BAD_REQUEST);
	    } catch (QuestionarioSemNomeException e) {
	    	return new ResponseEntity<Questionario>(HttpStatus.BAD_REQUEST);
	    } catch (QuestaoInvalidaException e) {
	    	return new ResponseEntity<Questionario>(HttpStatus.BAD_REQUEST);
		} catch (ParametroInvalidoException e) {
			return new ResponseEntity<Questionario>(HttpStatus.BAD_REQUEST);
		}
	    
	    return new ResponseEntity<Questionario>(HttpStatus.CREATED);
	}
}
