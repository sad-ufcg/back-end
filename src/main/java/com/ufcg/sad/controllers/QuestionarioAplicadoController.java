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

import com.ufcg.sad.exceptions.EntidadeInvalidaException;
import com.ufcg.sad.exceptions.EntidadeNotFoundException;
import com.ufcg.sad.exceptions.ParametroInvalidoException;
import com.ufcg.sad.models.questionario.QuestionarioAplicado;
import com.ufcg.sad.services.questionario.QuestionarioAplicadoService;

/**
 * Controller para a entidade QuestionarioAplicado.
 * 
 * @author Arthur Vinícius
 */
@RestController
@RequestMapping("/questionariosAplicados")
@CrossOrigin
public class QuestionarioAplicadoController {

	@Autowired
	private QuestionarioAplicadoService questionarioAplicadoService;
	
	/**
	 * Método para criar um questionário aplicado.
	 * @param questionarioAplicado
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> criaQuestionarioAplicado(@RequestBody QuestionarioAplicado questionarioAplicado) {
	    try {
	    	QuestionarioAplicado questionarioAplicadoCriado = questionarioAplicadoService.criaQuestionarioAplicado(questionarioAplicado);
	    	return new ResponseEntity<Object>(questionarioAplicadoCriado, HttpStatus.CREATED);	
	    } catch (ParametroInvalidoException | EntidadeInvalidaException | EntidadeNotFoundException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * Método para recuperar um questionário aplicado a partir de um certo id.
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> getQuestionarioAplicado(@PathVariable("id") Long id) {
		try {
			QuestionarioAplicado questionarioAplicado = questionarioAplicadoService.getQuestionarioAplicado(id);
			return new ResponseEntity<Object>(questionarioAplicado, HttpStatus.OK);
		} catch (EntidadeNotFoundException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}	
	
	/**
	 * Método para recuperar todos os questionários aplicados.
	 */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<QuestionarioAplicado>> getTodosQuestionariosAplicados() {
        List<QuestionarioAplicado> questionariosAplicado = questionarioAplicadoService.getTodosQuestionariosAplicados();
        return new ResponseEntity<List<QuestionarioAplicado>>(questionariosAplicado, HttpStatus.OK);
    }
	
	/**
	 * Método para atualizar um questionário aplicado.
	 * @param questionarioAplicado
	 */
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public ResponseEntity<Object> atualizaQuestionarioAplicado(@RequestBody QuestionarioAplicado questionarioAplicado) {
		try {
			QuestionarioAplicado questionarioAplicadoAtualizado = questionarioAplicadoService.atualizaQuestionarioAplicado(questionarioAplicado);
			return new ResponseEntity<Object>(questionarioAplicadoAtualizado, HttpStatus.OK);
		} catch (EntidadeNotFoundException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (EntidadeInvalidaException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
