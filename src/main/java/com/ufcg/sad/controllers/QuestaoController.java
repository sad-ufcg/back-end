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

import com.ufcg.sad.exceptions.EntidadeInvalidaException;
import com.ufcg.sad.exceptions.EntidadeNotFoundException;
import com.ufcg.sad.models.questao.Questao;
import com.ufcg.sad.services.questao.QuestaoService;

/**
 * Este controller provê uma API para acessar questionários.
 * 
 * @author Marianne Linhares
 */
@RestController
@RequestMapping("/questoes")
public class QuestaoController {

	@Autowired
	private QuestaoService questaoService;
	
	/**
	 * Método para recuperar um questão a partir de um certo id.
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> getQuestionario(@PathVariable("id") Long id) {
		try {
			Questao questao = questaoService.getQuestao(id);
			return new ResponseEntity<Object>(questao, HttpStatus.OK);
		} catch (EntidadeNotFoundException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}	
	/**
	 * Método para atualizar uma questão a partir de um certo id.
	 * @param id
	 */
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public ResponseEntity<Object> atualizaQuestao(@RequestBody Questao questao) {
		try {
			Questao questaoAtualizada = questaoService.atualizaQuestao(questao);
			return new ResponseEntity<Object>(questaoAtualizada, HttpStatus.OK);
		} catch (EntidadeNotFoundException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (EntidadeInvalidaException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Método para recuperar todas as questões.
	 */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Questao> > getTodasQuestoes() {
        List<Questao> questoes = questaoService.getTodasQuestoes();
        return new ResponseEntity<List<Questao> >(questoes, HttpStatus.OK);
    }

    /**
	 * Método para criar uma questão.
	 * @param questao
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> criaQuestao(@RequestBody Questao questao) {
	    try {
	    	Questao questaoCriada = questaoService.criaQuestao(questao);
	    	return new ResponseEntity<Object>(questaoCriada, HttpStatus.CREATED);	
	    } catch (EntidadeInvalidaException e) {
	    	return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
	    } catch (EntidadeNotFoundException e) {
	    	return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
	    }
	}
}
