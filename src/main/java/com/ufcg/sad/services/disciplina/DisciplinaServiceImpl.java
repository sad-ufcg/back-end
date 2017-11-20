package com.ufcg.sad.services.disciplina;

import com.ufcg.sad.exceptions.EntidadeNotFoundException;
import com.ufcg.sad.models.disciplina.Disciplina;
import com.ufcg.sad.repositories.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinaServiceImpl implements DisciplinaService {

    private final String NAO_ENCONTRADO = "disciplina não encontrada";

    @Autowired
    DisciplinaRepository disciplinaRepository;

    @Override
    public Disciplina cadastrarDisciplina(Disciplina disciplina) {
        return disciplinaRepository.save(disciplina);
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
}
