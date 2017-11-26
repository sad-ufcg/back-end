package com.ufcg.sad.services;

import com.ufcg.sad.models.aluno.Aluno;
import com.ufcg.sad.repositories.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatriculaServiceImpl implements MatriculaService {

    @Autowired
    MatriculaRepository matriculaRepository;

    @Override
    public List<Aluno> getAlunosMatriculados(Long idDisciplina) {
        return matriculaRepository.getAlunosMatriculados(idDisciplina);
    }
}
