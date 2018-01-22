package com.ufcg.sad.services.professor;

import com.ufcg.sad.exceptions.EntidadeInvalidaException;
import com.ufcg.sad.exceptions.EntidadeNotFoundException;
import com.ufcg.sad.models.disciplina.Disciplina;
import com.ufcg.sad.models.professor.Professor;
import com.ufcg.sad.repositories.ProfessorRepository;
import com.ufcg.sad.services.disciplina.DisciplinaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorServiceImpl implements ProfessorService {

	private final String REGEX_SIAPE = "\\d{7}$"; // 7 dígitos

	@Autowired
    DisciplinaService disciplinaService;
	
    @Autowired
    ProfessorRepository professorRepository;

    private String getNotFoundString(String parametroProcura, String itemProcurado) {
    	return "Professor com " + parametroProcura + " " + itemProcurado + "não existe.";
    }
    
    public void validaProfessor(Professor professor) throws EntidadeInvalidaException, EntidadeNotFoundException { 	
    	if(professor.getSiape() == null || !professor.getSiape().matches(REGEX_SIAPE)) {
    		throw new EntidadeInvalidaException("Siape inválido: " + professor.getSiape());
    	}

    	if(professor.getNome() == null) {
    		throw new EntidadeInvalidaException("Nome do professor não pode ser Null.");
    	}

    	if(professor.getDisciplinas() != null) {
    		for(Disciplina d: professor.getDisciplinas()) {
    			if(d == null) {
    				throw new EntidadeInvalidaException("Disciplina não pode ser Null");
    			}
    			else {
    				disciplinaService.validaDisciplina(d);
    			}
    		}
    	}
    }
    
    private void validaCriacaoProfessor(Professor professor) throws EntidadeInvalidaException, EntidadeNotFoundException {
    	if(professor.getId() != null) {
    		throw new EntidadeInvalidaException("Id deve ser nulo para criação de Professor.");
    	}
    	validaProfessor(professor);
	}
    
    @Override
    public List<Professor> getTodosOsProfessores() {
        return professorRepository.findAll();
    }

    @Override
    public Professor atualizarProfessor(Professor professor) throws EntidadeNotFoundException, EntidadeInvalidaException {     
    	validaProfessor(professor);
    	
    	if(professorRepository.exists(professor.getId())) {
            return professorRepository.save(professor);
        } else {
            throw new EntidadeNotFoundException(
            		this.getNotFoundString("id", String.valueOf(professor.getId())));
        }
    }

    @Override
    public Professor criarProfessor(Professor professor) throws EntidadeInvalidaException, EntidadeNotFoundException {
    	validaCriacaoProfessor(professor);
        Professor professorSalvo = professorRepository.save(professor);
        for(Disciplina d: professor.getDisciplinas()) {
        	d.setProfessorId(professorSalvo.getId());
        	disciplinaService.atualizarDisciplina(d);
        }
        return professorSalvo;
    }

	@Override
    public Professor getProfessor(Long id) throws EntidadeNotFoundException {
        if(id != null && professorRepository.exists(id)) {
            return professorRepository.findOne(id);
        } else {
        	throw new EntidadeNotFoundException(
            		this.getNotFoundString("id", String.valueOf(id)));
        }
    }

    @Override
    public void removerProfessor(Professor professor) throws EntidadeNotFoundException {
        if(professor != null && professorRepository.exists(professor.getId())) {
            professorRepository.delete(professor.getId());
        } else {
        	throw new EntidadeNotFoundException(
            		this.getNotFoundString("id", String.valueOf(professor.getId())));
        }
    }

    @Override
    public Professor procurarProfessorPeloSiape(String siape) throws EntidadeNotFoundException {
    	Professor professor = professorRepository.findBySiape(siape);
        if(professor != null) {
            return professor;
        } else {
        	throw new EntidadeNotFoundException(this.getNotFoundString("siape", siape));
        }
    }
}
