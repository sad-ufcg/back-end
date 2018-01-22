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
    
    private final String REGEX_SEMESTRE = "\\d+[.]\\d{1}$"; // Número qualquer + '.' + 1 dígito
    private final String REGEX_CODIGO = "\\d{7}$"; // 7 dígitos

    @Autowired
    DisciplinaRepository disciplinaRepository;

    @Autowired
    AlunoService alunoService;

    @Autowired
    ProfessorService professorService;
    
    public void validaDisciplina(Disciplina disciplina) throws EntidadeNotFoundException, EntidadeInvalidaException {
    	// Checa se professor existe
    	if(disciplina.getProfessorId() != null) {
    		 professorService.getProfessor(disciplina.getProfessorId());
    	}
    	
    	if(disciplina.getTurma() == null) {
    		throw new EntidadeInvalidaException("Turma inválida: " + disciplina.getTurma());
    	}
    	
    	if(disciplina.getSemestre() == null || !disciplina.getSemestre().matches(REGEX_SEMESTRE)) {
    		throw new EntidadeInvalidaException("Semestre inválido: " + disciplina.getSemestre());
    	}
    	
    	if(disciplina.getCodigo() != null && !disciplina.getCodigo().matches(REGEX_CODIGO)) {
    		throw new EntidadeInvalidaException("Código inválido: " + disciplina.getCodigo());
    	}
    }
    
    private void validaCriacaoDisciplina(Disciplina disciplina) throws EntidadeInvalidaException, EntidadeNotFoundException {
    	if(disciplina.getId() != null) {
    		throw new EntidadeInvalidaException("Id deve ser nulo para a criação de Disciplina.");
    	}
    	
    	validaDisciplina(disciplina);
    }
    
    
    @Override
    public Disciplina cadastrarDisciplina(Disciplina disciplina) throws EntidadeNotFoundException, EntidadeInvalidaException {
    	
    	validaCriacaoDisciplina(disciplina);
    	
    	// Salva disciplina
    	Disciplina disciplinaSalva = disciplinaRepository.save(disciplina);
 	
    	// Adiciona disciplina a professor se for preciso
    	Professor professor;
    	if(disciplinaSalva.getProfessorId() != null) {
    		professor = professorService.getProfessor(disciplinaSalva.getProfessorId());
	    	professor.addDisciplina(disciplinaSalva);
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
    public Disciplina atualizarDisciplina(Disciplina disciplina) throws EntidadeNotFoundException, EntidadeInvalidaException {
    	validaDisciplina(disciplina);
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
