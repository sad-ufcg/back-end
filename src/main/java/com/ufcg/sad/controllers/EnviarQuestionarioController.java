package com.ufcg.sad.controllers;

import com.ufcg.sad.exceptions.EntidadeInvalidaException;
import com.ufcg.sad.exceptions.EntidadeNotFoundException;
import com.ufcg.sad.services.questionario.EnviarQuestionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mail")
@CrossOrigin
public class EnviarQuestionarioController {

    @Autowired
    EnviarQuestionarioService enviarQuestionarioService;

    /**
     * Envia um Questionário Aplicado.
     * @param id
     *          Id do Questionário a ser enviado.
     * @return
     *          Código HTTP informando resultado da operação.
     */
    @RequestMapping(value = "{/id}", method = RequestMethod.POST)
    public ResponseEntity<Object> enviarEmailParaUmQuestionario(@PathVariable("id") Long id) {
        try {
            enviarQuestionarioService.enviarEmail(id);
            return new ResponseEntity<Object>(HttpStatus.OK);
        } catch (EntidadeNotFoundException e) {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (EntidadeInvalidaException e) {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
    }

    /**
     * Envia vários Questionários Aplicados
     * @param ids
     *          Lista com os ids dos Questionários a serem enviados.
     * @return
     *          Código HTTP informando o resultado da operação.
     */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> enviarEmailParaVariosQuestionarios(@RequestBody List<Long> ids) {
        try {
        	enviarQuestionarioService.enviarEmail(ids);
            return new ResponseEntity<Object>(HttpStatus.OK);
        } catch (EntidadeNotFoundException e) {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (EntidadeInvalidaException e) {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
    }

}
