package com.ufcg.sad.models.util;

import com.ufcg.sad.models.aluno.Aluno;
import com.ufcg.sad.models.disciplina.Disciplina;
import com.ufcg.sad.models.matricula.Matricula;

public final class Utils {

    public static final int TAMANHO_MAX_STRING = 255;


    public static Matricula createMatriculaTest(Long id, String nomeAluno, String nomeDisciplina) {
        Aluno aluno = new Aluno();
        aluno.setNome(nomeAluno);
        aluno.setEmail("algumemail@ccc.ufcg.edu.br");

        Disciplina disciplina = new Disciplina();
        disciplina.setNome(nomeDisciplina);
        disciplina.setTurma(2);
        disciplina.setSemestre("2017.2");
        Matricula matricula = new Matricula(aluno, disciplina);

        return matricula;
    }
}