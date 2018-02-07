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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.sad.exceptions.EntidadeInvalidaException;
import com.ufcg.sad.exceptions.EntidadeNotFoundException;
import com.ufcg.sad.models.disciplina.Disciplina;
import com.ufcg.sad.models.questionario.Questionario;
import com.ufcg.sad.models.questionario.QuestionarioAplicado;
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
	public ResponseEntity<Object> getQuestionario(@PathVariable("id") Long id) {
		try {
			Questionario questionario = questionarioService.getQuestionario(id);
			return new ResponseEntity<Object>(questionario, HttpStatus.OK);
		} catch (EntidadeNotFoundException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * Método para recuperar todas as respostas de um questionário a partir de um certo id.
	 * @param id
	 */
	@RequestMapping(value = "/{id}/questionariosAplicados/", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getQuestionariosAplicadosByIDs(@PathVariable("id") Long id,
																 @RequestParam(value = "semestre", required = false) String semestre,
																 @RequestParam(value = "idDisciplina", required = false) Long idDisciplina) {
		List<QuestionarioAplicado> questionario = questionarioService.getQuestionariosAplicados(id, semestre, idDisciplina);
		return new ResponseEntity<Object>(questionario, HttpStatus.OK);
	}
	
	/**
	 * Método para recuperar todos as disciplinas associadas a um
	 * questionário por meio de um questionário aplicado.
	 * @param id
	 */
	@RequestMapping(value = "/{id}/disciplinas/", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getDisciplinas(@RequestParam(value = "semestre", required = false) String semestre,
			                                     @PathVariable("id") Long id) {
		List<Disciplina> disciplinas;
		try {
			disciplinas = questionarioService.getDisciplinas(id, semestre);
		} catch (EntidadeNotFoundException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Object>(disciplinas, HttpStatus.OK);
	}
	
	/**
	 * Método para atualizar um Questionario a partir de um certo id.
	 * @param id
	 */
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public ResponseEntity<Object> atualizaQuestionario(@RequestBody Questionario questionario) {
		try {
			Questionario questionarioAtualizado = questionarioService.atualizaQuestionario(questionario);
			return new ResponseEntity<Object>(questionarioAtualizado, HttpStatus.OK);
		} catch (EntidadeNotFoundException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (EntidadeInvalidaException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
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
	public ResponseEntity<Object> criaQuestionario(@RequestBody Questionario questionario) {
	    try {
	    	Questionario questionarioCriado = questionarioService.criaQuestionario(questionario);
	    	return new ResponseEntity<Object>(questionarioCriado, HttpStatus.CREATED);	
	    } catch (EntidadeInvalidaException e) {
	    	return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
	    } catch (EntidadeNotFoundException e) {
	    	return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
	    }
	}
}
