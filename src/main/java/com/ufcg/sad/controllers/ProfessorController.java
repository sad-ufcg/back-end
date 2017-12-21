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
import com.ufcg.sad.models.professor.Professor;
import com.ufcg.sad.services.professor.ProfessorService;

/**
 * Controller para a entidade Professor.
 * 
 * @author Arthur Vinícius
 */
@RestController
@RequestMapping("/professores")
public class ProfessorController {

	@Autowired
	private ProfessorService professorService;
	
	/**
	 * Método para atualizar um professor.
	 * @param professor
	 */
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public ResponseEntity<Professor> atualizarProfessor(@RequestBody Professor professor) {
		try {
			Professor professorAtualizado = professorService.atualizarProfessor(professor);
			return new ResponseEntity<Professor>(professorAtualizado, HttpStatus.OK);
		} catch (EntidadeNotFoundException e) {
			return new ResponseEntity<Professor>(HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * Método para criar um professor.
	 * @param professor
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Professor> criarProfessor(@RequestBody Professor professor) {
		Professor professorCriado = professorService.criarProfessor(professor);
	    return new ResponseEntity<Professor>(professorCriado, HttpStatus.CREATED);
	}
	
	/**
	 * Método para recuperar um professor a partir de um certo id.
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Professor> getProfessor(@PathVariable("id") Long id) {
		try {
			Professor professor = professorService.getProfessor(id);
			return new ResponseEntity<Professor>(professor, HttpStatus.OK);
		} catch (EntidadeNotFoundException e) {
			return new ResponseEntity<Professor>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Método para recuperar todos os professores.
	 */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Professor>> getTodosOsProfessores() {
        List<Professor> professores = professorService.getTodosOsProfessores();
        return new ResponseEntity<List<Professor>>(professores, HttpStatus.OK);
    }
    
    /**
	 * Método para recuperar um professor a partir de um número Siape.
	 * @param siape
	 */
	@RequestMapping(value = "/{siape}", method = RequestMethod.GET)
	public ResponseEntity<Professor> procurarProfessorPeloSiape(@PathVariable("siape") String siape) {
		try {
			Professor professor = professorService.procurarProfessorPeloSiape(siape);
			return new ResponseEntity<Professor>(professor, HttpStatus.OK);
		} catch (EntidadeNotFoundException e) {
			return new ResponseEntity<Professor>(HttpStatus.NOT_FOUND);
		}
	}
}
