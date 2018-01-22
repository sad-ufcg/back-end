package com.ufcg.sad.models.disciplina;

import com.ufcg.sad.SadApplicationTests;
import com.ufcg.sad.models.matricula.Matricula;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Teste para o model disciplina
 */
public class DisciplinaTest extends SadApplicationTests {

    @Test
    public void testGetterESetter() {
        Long id = 1L;
        String nome = "Programação I";
        Integer turma = 2;
        Long professorId = 2L;
        String semestre = "20172";
        Set<Matricula> alunos = new HashSet<>();

        Disciplina disciplina = new Disciplina(id, nome, turma, professorId, semestre, alunos);

        Assert.assertEquals(id, disciplina.getId());
        Assert.assertEquals(nome, disciplina.getNome());
        Assert.assertEquals(turma, disciplina.getTurma());
        Assert.assertEquals(professorId, disciplina.getProfessorId());
        Assert.assertEquals(semestre, disciplina.getSemestre());
        Assert.assertEquals(alunos, disciplina.getMatriculas());
    }

}
