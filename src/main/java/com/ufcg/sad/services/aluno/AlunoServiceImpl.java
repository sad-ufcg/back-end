package com.ufcg.sad.services.aluno;

import com.ufcg.sad.exceptions.EntidadeNotFoundException;
import com.ufcg.sad.models.aluno.Aluno;
import com.ufcg.sad.repositories.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoServiceImpl implements AlunoService {

    @Autowired
    AlunoRepository alunoRepository;

    @Override
    public Aluno atualizarAluno(Aluno aluno) throws EntidadeNotFoundException{
        if(alunoRepository.exists(aluno.getId())) {
            return alunoRepository.save(aluno);
        } else {
            throw new EntidadeNotFoundException();
        }
    }

    @Override
    public Aluno criarAluno(Aluno aluno) {
        return alunoRepository.saveAndFlush(aluno);
    }

    @Override
    public Aluno getAluno(Long id) throws EntidadeNotFoundException {
        if(alunoRepository.exists(id)) {
            return alunoRepository.findOne(id);
        } else {
            throw new EntidadeNotFoundException();
        }
    }

    @Override
    public List<Aluno> getTodosOsAlunos() {
        return alunoRepository.findAll();
    }

    @Override
    public void removerAluno(Aluno aluno) throws EntidadeNotFoundException {
        if(alunoRepository.exists(aluno.getId())) {
            alunoRepository.delete(aluno);
        } else {
            throw new EntidadeNotFoundException();
        }
    }

    @Override
    public Aluno procurarPorEmail(String email) throws EntidadeNotFoundException {
        Aluno aluno = alunoRepository.findByEmail(email);
        return aluno;
    }
}
