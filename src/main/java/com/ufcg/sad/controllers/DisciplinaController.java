package com.ufcg.sad.controllers;

import com.ufcg.sad.exceptions.EntidadeNotFoundException;
import com.ufcg.sad.models.disciplina.Disciplina;
import com.ufcg.sad.services.disciplina.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller para a entidade disciplina.
 *
 * @author Antunes Dantas
 */
@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {

    @Autowired
    DisciplinaService disciplinaService;

    /**
     * Recupera uma disciplina cadastrada.
     *
     * @param id Id da disciplina a ser recuperada.
     *
     * @return disciplina recuperada.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Disciplina> getDisciplina(@PathVariable("id") Long id) {
        try {
            Disciplina disciplina = disciplinaService.getDisciplina(id);
            return new ResponseEntity<Disciplina>(disciplina, HttpStatus.OK);
        } catch (EntidadeNotFoundException e) {
           return new ResponseEntity<Disciplina>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Cadastra uma nova disciplina no sistema.
     *
     * @param disciplina disciplina a ser cadastrada.
     *
     * @return disciplina cadastrada.
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Disciplina> cadastrarDisciplina(@RequestBody Disciplina disciplina) {
        Disciplina disciplinaCadastrada = disciplinaService.cadastrarDisciplina(disciplina);

        return new ResponseEntity<Disciplina>(disciplinaCadastrada, HttpStatus.CREATED);
    }

    /**
     * Recupera todas as disciplinas cadastradas
     *
     * @return Uma lista com todas as Disciplinas cadastradas.
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Disciplina>> getAll() {
        List<Disciplina> disciplinas = disciplinaService.listarTodasAsDisciplinas();

        return new ResponseEntity<List<Disciplina>>(disciplinas, HttpStatus.OK);
    }

}
