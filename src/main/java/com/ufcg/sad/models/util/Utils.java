package com.ufcg.sad.models.util;

import com.ufcg.sad.models.aluno.Aluno;
import com.ufcg.sad.models.disciplina.Disciplina;
import com.ufcg.sad.models.matricula.Matricula;

public final class Utils {

    public static final int TAMANHO_MAX_STRING = 255;


    public static Matricula createMatriculaTest(String nomeAluno, String nomeDisciplina) {
        Aluno aluno = new Aluno();
        aluno.setNome(nomeAluno);
        Disciplina disciplina = new Disciplina();
        disciplina.setNome(nomeDisciplina);
        Matricula matricula = new Matricula(aluno, disciplina);

        return matricula;
    }
}