package com.ufcg.sad.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sampaio on 15/11/17.
 */
@RestController
@RequestMapping("/testes")
public class ControllerParaTestes {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<String> testParaGet(){
        return new ResponseEntity<String>("OK!", HttpStatus.FOUND);
    }


}
