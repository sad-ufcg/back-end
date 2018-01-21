package com.ufcg.sad.services.professor;

import com.ufcg.sad.exceptions.EntidadeNotFoundException;
import com.ufcg.sad.models.professor.Professor;
import com.ufcg.sad.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    @Autowired
    ProfessorRepository professorRepository;

    
    private String getNotFoundString(String parametroProcura, String itemProcurado) {
    	return "Professor com " + parametroProcura + " " + itemProcurado + "não existe.";
    }
    
    @Override
    public List<Professor> getTodosOsProfessores() {
        return professorRepository.findAll();
    }

    @Override
    public Professor atualizarProfessor(Professor professor) throws EntidadeNotFoundException {
        if(professorRepository.exists(professor.getId())) {
            return professorRepository.save(professor);
        } else {
            throw new EntidadeNotFoundException(
            		this.getNotFoundString("id", String.valueOf(professor.getId())));
        }
    }

    @Override
    public Professor criarProfessor(Professor professor) {
        return professorRepository.save(professor);
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
