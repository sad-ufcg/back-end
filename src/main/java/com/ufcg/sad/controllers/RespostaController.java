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
import com.ufcg.sad.models.resposta.Resposta;
import com.ufcg.sad.services.resposta.RespostaService;

/**
 * Controller para a entidade Resposta.
 * 
 * @author Arthur Vinícius
 */
@RestController
@RequestMapping("/respostas")
@CrossOrigin
public class RespostaController {
	
	@Autowired
	private RespostaService respostaService;	
	
	/**
	 * Método para criar uma resposta.
	 * @param resposta
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> criarResposta(@RequestBody Resposta resposta) {
	    Resposta respostaCriada;
		try {
			respostaCriada = respostaService.criarResposta(resposta);
		} catch (EntidadeNotFoundException | EntidadeInvalidaException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	    return new ResponseEntity<Object>(respostaCriada, HttpStatus.CREATED);
	}
	
	/**
	 * Método para recuperar uma resposta a partir de um certo id.
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> getResposta(@PathVariable("id") Long id) {
		try {
			Resposta resposta = respostaService.getResposta(id);
			return new ResponseEntity<Object>(resposta, HttpStatus.OK);
		} catch (EntidadeNotFoundException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Método para recuperar todas as respostas.
	 */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Resposta>> getTodasAsRespostas(
    			@RequestParam(value="tipoResposta", required=false) String tipoResposta,
    	        @RequestParam(value="idQuestao", required=false) Long idQuestao) {
        List<Resposta> respostas = respostaService.getTodasAsRespostas(tipoResposta, idQuestao);
        return new ResponseEntity<List<Resposta>>(respostas, HttpStatus.OK);
    }
    
    /**
     * Método para adicionar uma lista de respostas.
     * @param respostas
     */
    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> addRespostas(@RequestParam("token") String token,
    		                                   @RequestBody List<Resposta> respostas) {
    	List<Resposta> ret = null;
		try {
			ret = respostaService.addRespostas(token, respostas);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
    	return new ResponseEntity<Object>(ret, HttpStatus.CREATED);
    }
}
