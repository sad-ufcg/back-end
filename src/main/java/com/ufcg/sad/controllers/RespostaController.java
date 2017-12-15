package com.ufcg.sad.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
public class RespostaController {
	
	@Autowired
	private RespostaService respostaService;	
	
	/**
	 * Método para criar uma resposta.
	 * @param resposta
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Resposta> criarResposta(@RequestBody Resposta resposta) {
	    Resposta respostaCriada = respostaService.criarResposta(resposta);
	    return new ResponseEntity<Resposta>(respostaCriada, HttpStatus.CREATED);
	}
	
	/**
	 * Método para recuperar uma resposta a partir de um certo id.
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Resposta> getResposta(@PathVariable("id") Long id) {
		try {
			Resposta resposta = respostaService.getResposta(id);
			return new ResponseEntity<Resposta>(resposta, HttpStatus.OK);
		} catch (EntidadeNotFoundException e) {
			return new ResponseEntity<Resposta>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Método para recuperar todas as respostas.
	 */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Resposta>> getTodasAsRespostas() {
        List<Resposta> respostas = respostaService.getTodasAsRespostas();
        return new ResponseEntity<List<Resposta>>(respostas, HttpStatus.OK);
    }
    
    /**
     * Método para adicionar uma lista de respostas.
     * @param respostas
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<List<Resposta>> addRespostas(@RequestBody List<Resposta> respostas) {
    	List<Resposta> ret = respostaService.addRespostas(respostas);
    	return new ResponseEntity<List<Resposta>>(ret, HttpStatus.CREATED);
    }
}
