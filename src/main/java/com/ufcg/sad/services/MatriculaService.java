package com.ufcg.sad.services;

import com.ufcg.sad.models.aluno.Aluno;

import java.util.List;

public interface MatriculaService {

    /**
     * Recupera os alunos matriculados em uma disciplina
     *
     * @param idDisciplina Disciplina procurada.
     *
     * @return Lista de alunos
     */
    List<Aluno> getAlunosMatriculados(Long idDisciplina);

}
