package com.ufcg.sad.controllers;

import com.ufcg.sad.exceptions.EntidadeNotFoundException;
import com.ufcg.sad.models.token.Token;
import com.ufcg.sad.services.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Token> checaSeTokenExiste(@RequestParam("tokenID") String tokenId) throws Exception {

        Token encontrado = tokenService.verificaSeTokenExiste(tokenId);

        if(encontrado !=null){
            return new ResponseEntity(encontrado, HttpStatus.OK);
        }else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }

}
