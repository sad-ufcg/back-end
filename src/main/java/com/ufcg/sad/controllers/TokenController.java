package com.ufcg.sad.controllers;

import com.ufcg.sad.exceptions.EntidadeNotFoundException;
import com.ufcg.sad.models.token.Token;
import com.ufcg.sad.services.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sampaio on 21/11/17.
 */
@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    TokenService tokenService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Token> checaSeTokenExiste(@PathVariable("tokenID") String tokenId) {
        try {
            Token encontrado = tokenService.verificaSeTokenExiste(tokenId);
            return new ResponseEntity<Token>(encontrado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Token>(HttpStatus.NOT_FOUND);
        }
    }

}
