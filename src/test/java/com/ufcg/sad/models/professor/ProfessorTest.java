package com.ufcg.sad.models.professor;

import com.ufcg.sad.SadApplicationTests;
import com.ufcg.sad.models.disciplina.Disciplina;
import com.ufcg.sad.models.questionario.QuestionarioAplicado;
import org.junit.Assert;

import java.util.HashSet;
import java.util.Set;

/**
 * Classe de teste para Professor.
 */
public class ProfessorTest extends SadApplicationTests {

    public void testGetterESetter() {
        String siape = "111111";
        String nome = "Professor da Silva";
        Set<Disciplina> disciplinas = new HashSet<>();
        QuestionarioAplicado questionarioAplicado = new QuestionarioAplicado();

        Professor professor = new Professor(siape, nome, disciplinas, questionarioAplicado);

        Assert.assertEquals(siape, professor.getSiape());
        Assert.assertEquals(nome, professor.getNome());
        Assert.assertEquals(disciplinas, professor.getDisciplinas());
    }

}
