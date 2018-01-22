package com.ufcg.sad.controllers;

import com.ufcg.sad.exceptions.EntidadeInvalidaException;
import com.ufcg.sad.exceptions.EntidadeNotFoundException;
import com.ufcg.sad.models.aluno.Aluno;
import com.ufcg.sad.models.disciplina.Disciplina;
import com.ufcg.sad.services.MatriculaService;
import com.ufcg.sad.services.disciplina.DisciplinaService;
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

/**
 * Controller para a entidade disciplina.
 *
 * @author Antunes Dantas
 */
@RestController
@RequestMapping("/disciplinas")
@CrossOrigin
public class DisciplinaController {

    @Autowired
    DisciplinaService disciplinaService;

    @Autowired
    MatriculaService matriculaService;

    /**
     * Recupera uma disciplina cadastrada.
     *
     * @param id Id da disciplina a ser recuperada.
     *
     * @return disciplina recuperada.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getDisciplina(@PathVariable("id") Long id) {
        try {
            Disciplina disciplina = disciplinaService.getDisciplina(id);
            return new ResponseEntity<Object>(disciplina, HttpStatus.OK);
        } catch (EntidadeNotFoundException e) {
           return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Cadastra uma nova disciplina no sistema.
     *
     * @param disciplina disciplina a ser cadastrada.
     *
     * @return disciplina cadastrada.
     */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> cadastrarDisciplina(@RequestBody Disciplina disciplina) {
        Disciplina disciplinaCadastrada;
		try {
			disciplinaCadastrada = disciplinaService.cadastrarDisciplina(disciplina);
		} catch (EntidadeNotFoundException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (EntidadeInvalidaException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
        return new ResponseEntity<Object>(disciplinaCadastrada, HttpStatus.CREATED);
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

    /**
     * Cadastra um Aluno em uma Disciplina já existente.
     *
     * @param id Id da Disciplina.
     * @param aluno Aluno a ser adicionado.
     *
     * @return Disciplina atualizada.
     */
    @RequestMapping(value = "/{id}/alunos", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> cadastrarAlunoEmDisciplina(@PathVariable("id") Long id,
                                                                 @RequestBody Aluno aluno) {
        try {
            Aluno alunoCadastrado = disciplinaService.vincularAluno(id, aluno);

            return new ResponseEntity<Object>(alunoCadastrado, HttpStatus.CREATED);
        } catch (EntidadeNotFoundException e) {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch(EntidadeInvalidaException e) {
        	return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Recupera os alunos matriculados em certa disciplina.
     *
     * @param id Id da Disciplina.
     *
     * @return Lista de alunos
     */
    @RequestMapping(value = "/{id}/alunos", method = RequestMethod.GET)
    public ResponseEntity<List<Aluno>> getAlunosMatriculados(@PathVariable("id") Long id) {
        List<Aluno> alunos = matriculaService.getAlunosMatriculados(id);

        return new ResponseEntity<List<Aluno>>(alunos, HttpStatus.OK);
    }
}
