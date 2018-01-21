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

    @Override
    public List<Professor> getTodosOsProfessores() {
        return professorRepository.findAll();
    }

    @Override
    public Professor atualizarProfessor(Professor professor) throws EntidadeNotFoundException {
        if(professorRepository.exists(professor.getId())) {
            return professorRepository.save(professor);
        } else {
            throw new EntidadeNotFoundException();
        }
    }

    @Override
    public Professor criarProfessor(Professor professor) {
        return professorRepository.save(professor);
    }

    @Override
    public Professor getProfessor(Long id) throws EntidadeNotFoundException {
        if(professorRepository.exists(id)) {
            return professorRepository.findOne(id);
        } else {
            throw new EntidadeNotFoundException();
        }
    }

    @Override
    public void removerProfessor(Professor professor) throws EntidadeNotFoundException {
        if(professorRepository.exists(professor.getId())) {
            professorRepository.delete(professor.getId());
        } else {
            throw new EntidadeNotFoundException();
        }
    }

    @Override
    public Professor procurarProfessorPeloSiape(String siape) throws EntidadeNotFoundException {
    	Professor professor = professorRepository.findBySiape(siape);
        if(professor != null) {
            return professor;
        } else {
            throw new EntidadeNotFoundException();
        }
    }
}
