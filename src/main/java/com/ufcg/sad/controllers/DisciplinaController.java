package com.ufcg.sad.controllers;

import com.ufcg.sad.exceptions.EntidadeNotFoundException;
import com.ufcg.sad.models.disciplina.Disciplina;
import com.ufcg.sad.services.questionario.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller para a entidade Disciplina.
 *
 * @author Antunes Dantas
 */
@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {

    @Autowired
    DisciplinaService disciplinaService;

    /**
     * Recupera uma Disciplina cadastrada.
     *
     * @param id Id da Disciplina a ser recuperada.
     *
     * @return Disciplina recuperada.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Disciplina> getDisciplina(@PathVariable("id") Long id) {
        try {
            Disciplina disciplina = disciplinaService.getDisciplina(id);
            return new ResponseEntity<Disciplina>(disciplina, HttpStatus.OK);
        } catch (EntidadeNotFoundException e) {
           return new ResponseEntity(e, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Cadastra uma nova Disciplina no sistema.
     *
     * @param disciplina Disciplina a ser cadastrada.
     *
     * @return Disciplina cadastrada.
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Disciplina> cadastrarDisciplina(@RequestBody Disciplina disciplina) {
        Disciplina disciplinaCadastrada = disciplinaService.cadastrarDisciplina(disciplina);

        return new ResponseEntity<Disciplina>(disciplinaCadastrada, HttpStatus.CREATED);
    }

}
