package com.ufcg.sad.models.matricula;

import com.ufcg.sad.models.aluno.Aluno;
import com.ufcg.sad.models.disciplina.Disciplina;
import org.junit.Assert;
import org.junit.Test;

/**
 * Classe de teste para a entidade Matrícula
 */
public class MatriculaTest {

    @Test
    public void testGetterESetter() {
        Aluno aluno = new Aluno();
        String nomeAluno = "Aluno";
        aluno.setNome(nomeAluno);

        Disciplina disciplina = new Disciplina();
        String nomeDisciplina = "Disciplina";
        disciplina.setNome(nomeDisciplina);

        Long idMatricula = 2L;

        Matricula matricula = new Matricula(idMatricula, aluno, disciplina);

        Assert.assertEquals(idMatricula, matricula.getId());
        Assert.assertEquals(aluno, matricula.getAluno());
        Assert.assertEquals(disciplina, matricula.getDisciplina());
    }
}
