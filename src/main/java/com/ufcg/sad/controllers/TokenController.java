package com.ufcg.sad.controllers;

import com.ufcg.sad.exceptions.utils.ParametroInvalidoException;
import com.ufcg.sad.models.token.Token;
import com.ufcg.sad.services.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by sampaio on 21/11/17.
 */
@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    TokenService tokenService;

    /**
  	 * Método para checar se um token existe.
  	 * @param token
  	 */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Token> checaSeTokenExiste(@RequestParam("tokenID") String tokenId) throws Exception {

        Token encontrado = tokenService.verificaSeTokenExiste(tokenId);

        if(encontrado != null){
            return new ResponseEntity<Token>(encontrado, HttpStatus.OK);
        }else {
            return new ResponseEntity<Token>(HttpStatus.NOT_FOUND);
        }

    }
    
    /**
  	 * Método para criar um token.
  	 * @param token
  	 */
  	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  	@ResponseStatus(HttpStatus.CREATED)
  	public ResponseEntity<Token> criaToken(@RequestBody Token token) {
  	    try {
  	    	Token tokenCriado = tokenService.criaToken(token);
  	    	return new ResponseEntity<Token>(tokenCriado, HttpStatus.CREATED);	
  	    } catch (ParametroInvalidoException e) {
  			return new ResponseEntity<Token>(HttpStatus.BAD_REQUEST);
  		}
  	}
}
