package com.ufcg.sad.models.professor;

import com.ufcg.sad.models.disciplina.Disciplina;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Classe de teste de Professor
 */
public class ProfessorTest {

    @Test
    public void testGetterESetter() {
        String siape = "111111";
        String nome = "Professor da Silva";
        Set<Disciplina> disciplinas = new HashSet<>();

        Professor professor = new Professor(siape, nome, disciplinas);

        Assert.assertEquals(siape, professor.getSiape());
        Assert.assertEquals(nome, professor.getNome());
        Assert.assertEquals(disciplinas, professor.getDisciplinas());
    }

}
