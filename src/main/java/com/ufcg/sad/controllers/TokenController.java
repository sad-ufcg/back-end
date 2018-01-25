package com.ufcg.sad.controllers;

import com.ufcg.sad.exceptions.EntidadeInvalidaException;
import com.ufcg.sad.exceptions.EntidadeNotFoundException;
import com.ufcg.sad.exceptions.ParametroInvalidoException;
import com.ufcg.sad.models.disciplina.Disciplina;
import com.ufcg.sad.models.questionario.Questionario;
import com.ufcg.sad.models.questionario.QuestionarioAplicado;
import com.ufcg.sad.models.token.Token;
import com.ufcg.sad.services.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sampaio on 21/11/17.
 */
@RestController
@RequestMapping("/token")
@CrossOrigin
public class TokenController {

    @Autowired
    TokenService tokenService;

    /**
  	 * Método que retorna um questionario caso o token exista.
  	 * @param tokenId
  	 */
    @RequestMapping(value = "/questionario", method = RequestMethod.GET)
    public ResponseEntity<Object> buscaQuestionario(@RequestParam("tokenID") String tokenId) throws Exception {
    	try {
			Questionario questionario = tokenService.buscarQuestionario(tokenId);
			return new ResponseEntity<>(questionario, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
    }
    
    /**
  	 * Método que retorna um questionario aplicado caso o token exista.
  	 * @param tokenId
  	 */
    @RequestMapping(value = "/questionarioAplicado", method = RequestMethod.GET)
    public ResponseEntity<Object> buscaQuestionarioAplicado(@RequestParam("tokenID") String tokenId) throws Exception {
    	try {
			QuestionarioAplicado questionarioAplicado = tokenService.buscarQuestionarioAplicado(tokenId);
			return new ResponseEntity<>(questionarioAplicado, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
    }
    
    /**
  	 * Método que retorna uma disciplina associada ao questionario aplicado do token.
  	 * @param tokenId
  	 */
    @RequestMapping(value = "/disciplina", method = RequestMethod.GET)
    public ResponseEntity<Object> buscaDisciplina(@RequestParam("tokenID") String tokenId) throws Exception {
    	try {
			Disciplina disciplina = tokenService.buscarDisciplina(tokenId);
			return new ResponseEntity<>(disciplina, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
    }

    /**
  	 * Método para criar um token.
  	 * @param token
  	 */
  	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  	@ResponseStatus(HttpStatus.CREATED)
  	public ResponseEntity<Object> criaToken(@RequestBody Token token) {
  	    try {
  	    	Token tokenCriado = tokenService.criaToken(token);
  	    	return new ResponseEntity<>(tokenCriado, HttpStatus.CREATED);	
  	    } catch (ParametroInvalidoException | EntidadeNotFoundException | EntidadeInvalidaException e) {
  			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
  		}
  	}
  	
  	/**
  	 * Método para deletar um token.
  	 * @param token
  	 */
  	@RequestMapping(method = RequestMethod.DELETE)
  	@ResponseStatus(HttpStatus.ACCEPTED)
  	public ResponseEntity<Object> deletaToken(@RequestParam("tokenID") String tokenID) {
  	    try {
  	    	tokenService.deletaToken(tokenID);
  	    	return new ResponseEntity<Object>(null, HttpStatus.ACCEPTED);
  	    } catch (ParametroInvalidoException | EntidadeNotFoundException | EntidadeInvalidaException e) {
  			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
  		}
  	}
}
