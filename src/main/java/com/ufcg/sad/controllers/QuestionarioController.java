package com.ufcg.sad.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.sad.exceptions.EntidadeNotFoundException;
import com.ufcg.sad.exceptions.questionario.QuestaoInvalidaException;
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
@CrossOrigin
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
		} catch (EntidadeNotFoundException e) {
			return new ResponseEntity<Questionario>(HttpStatus.NOT_FOUND);
		}
	}	
	/**
	 * Método para atualizar um questionário a partir de um certo id.
	 * @param id
	 */
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public ResponseEntity<Questionario> atualizaQuestionario(@RequestBody Questionario questionario) {
		try {
			Questionario questionarioAtualizado = questionarioService.atualizaQuestionario(questionario);
			return new ResponseEntity<Questionario>(questionarioAtualizado, HttpStatus.OK);
		} catch (EntidadeNotFoundException e) {
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
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Questionario> criaQuestionario(@RequestBody Questionario questionario) {
	    try {
	    	Questionario questionarioCriado = questionarioService.criaQuestionario(questionario);
	    	return new ResponseEntity<Questionario>(questionarioCriado, HttpStatus.CREATED);	
	    } catch (QuestionarioVazioException e) {
	    	return new ResponseEntity<Questionario>(HttpStatus.BAD_REQUEST);
	    } catch (QuestionarioSemNomeException e) {
	    	return new ResponseEntity<Questionario>(HttpStatus.BAD_REQUEST);
	    } catch (QuestaoInvalidaException e) {
	    	return new ResponseEntity<Questionario>(HttpStatus.BAD_REQUEST);
		} catch (ParametroInvalidoException e) {
			return new ResponseEntity<Questionario>(HttpStatus.BAD_REQUEST);
		}
	}
}
