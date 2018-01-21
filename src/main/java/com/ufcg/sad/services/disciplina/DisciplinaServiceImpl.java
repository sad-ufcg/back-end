package com.ufcg.sad.services.disciplina;

import com.ufcg.sad.exceptions.EntidadeInvalidaException;
import com.ufcg.sad.exceptions.EntidadeNotFoundException;
import com.ufcg.sad.models.aluno.Aluno;
import com.ufcg.sad.models.disciplina.Disciplina;
import com.ufcg.sad.models.professor.Professor;
import com.ufcg.sad.repositories.DisciplinaRepository;
import com.ufcg.sad.services.aluno.AlunoService;
import com.ufcg.sad.services.professor.ProfessorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinaServiceImpl implements DisciplinaService {

    private final String NAO_ENCONTRADO = "disciplina não encontrada";

    @Autowired
    DisciplinaRepository disciplinaRepository;

    @Autowired
    AlunoService alunoService;

    @Autowired
    ProfessorService professorService;

    @Override
    public Disciplina cadastrarDisciplina(Disciplina disciplina) throws EntidadeNotFoundException {
    	// Salva disciplina
    	Disciplina disciplinaSalva = disciplinaRepository.save(disciplina);
 	
    	// Adiciona disciplina a professor
    	if(disciplinaSalva.getProfessorId() != null) {
	    	Professor professor = professorService.getProfessor(disciplinaSalva.getProfessorId());
	    	professor.addDisciplina(disciplina);
	    	// Salva professor
	    	professorService.atualizarProfessor(professor);
    	}
        return disciplinaSalva;
    }

    @Override
    public Disciplina getDisciplina(Long id) throws EntidadeNotFoundException {
        Disciplina disciplina = disciplinaRepository.findOne(id);
        if (disciplina != null) {
            return disciplina;
        } else {
            throw new EntidadeNotFoundException(NAO_ENCONTRADO);
        }
    }

    @Override
    public List<Disciplina> listarTodasAsDisciplinas() {
        return (List<Disciplina>) disciplinaRepository.findAll();
    }

    @Override
    public Disciplina atualizarDisciplina(Disciplina disciplina) {
        return disciplinaRepository.save(disciplina);
    }

    @Override
    public void removerDisciplina(Disciplina disciplina) throws EntidadeNotFoundException {
        if (disciplinaRepository.exists(disciplina.getId())) {
            disciplinaRepository.delete(disciplina);
        } else {
            throw new EntidadeNotFoundException(NAO_ENCONTRADO);
        }
    }

    @Override
    public Aluno vincularAluno(Long idDisciplina, Aluno aluno) throws EntidadeNotFoundException, EntidadeInvalidaException {
        Disciplina disciplina = disciplinaRepository.findOne(idDisciplina);

        Aluno novoAluno;
        try {
        	novoAluno = alunoService.procurarPorEmail(aluno.getEmail());
        } catch(EntidadeNotFoundException e) {
        	novoAluno = alunoService.criarAluno(aluno);
        }

        novoAluno.adicionarDisciplina(disciplina);
        alunoService.atualizarAluno(novoAluno);

        return novoAluno;
    }
}
