package com.ufcg.sad.models.matricula;

import com.ufcg.sad.SadApplicationTests;
import com.ufcg.sad.models.aluno.Aluno;
import com.ufcg.sad.models.disciplina.Disciplina;
import org.junit.Assert;
import org.junit.Test;

/**
 * Classe de teste para a entidade Matrícula
 */
public class MatriculaTest extends SadApplicationTests {

    @Test
    public void testGetterESetter() {
        Aluno aluno = new Aluno();
        String nomeAluno = "Aluno";
        aluno.setNome(nomeAluno);

        Disciplina disciplina = new Disciplina();
        String nomeDisciplina = "disciplina";
        disciplina.setNome(nomeDisciplina);

        Matricula matricula = new Matricula(aluno, disciplina);

        Assert.assertEquals(aluno, matricula.getAluno());
        Assert.assertEquals(disciplina, matricula.getDisciplina());
    }
}
