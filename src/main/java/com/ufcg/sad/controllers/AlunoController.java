package com.ufcg.sad.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.sad.exceptions.EntidadeNotFoundException;
import com.ufcg.sad.models.aluno.Aluno;
import com.ufcg.sad.services.aluno.AlunoService;

/**
 * Controller para a entidade Aluno.
 * 
 * @author Arthur Vinícius
 */
@RestController
@RequestMapping("/alunos")
public class AlunoController {

	@Autowired
	private AlunoService alunoService;	
	
	/**
	 * Método para atualizar um aluno.
	 * @param aluno
	 */
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public ResponseEntity<Aluno> atualizarAluno(@RequestBody Aluno aluno) {
		try {
			Aluno alunoAtualizado = alunoService.atualizarAluno(aluno);
			return new ResponseEntity<Aluno>(alunoAtualizado, HttpStatus.OK);
		} catch (EntidadeNotFoundException e) {
			return new ResponseEntity<Aluno>(HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * Método para criar um aluno.
	 * @param aluno
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Aluno> criarAluno(@RequestBody Aluno aluno) {
	    Aluno alunoCriado = alunoService.criarAluno(aluno);
	    return new ResponseEntity<Aluno>(alunoCriado, HttpStatus.CREATED);
	}
	
	/**
	 * Método para recuperar um aluno a partir de um certo id.
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Aluno> getAluno(@PathVariable("id") Long id) {
		try {
			Aluno aluno = alunoService.getAluno(id);
			return new ResponseEntity<Aluno>(aluno, HttpStatus.OK);
		} catch (EntidadeNotFoundException e) {
			return new ResponseEntity<Aluno>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Método para recuperar todos os alunos.
	 */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Aluno>> getTodosOsAlunos() {
        List<Aluno> alunos = alunoService.getTodosOsAlunos();
        return new ResponseEntity<List<Aluno>>(alunos, HttpStatus.OK);
    }
    
    /**
	 * Método para recuperar um aluno a partir de um email.
	 * @param email
	 */
	@RequestMapping(value = "/{email}", method = RequestMethod.GET)
	public ResponseEntity<Aluno> procurarPorEmail(@PathVariable("email") String email) {
		Aluno aluno = alunoService.procurarPorEmail(email);
		if (aluno != null) {
			return new ResponseEntity<Aluno>(aluno, HttpStatus.OK);
		} else {
			return new ResponseEntity<Aluno>(HttpStatus.NOT_FOUND);
		}
	}
}
