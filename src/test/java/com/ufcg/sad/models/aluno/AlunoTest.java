package com.ufcg.sad.models.aluno;

import com.ufcg.sad.models.disciplina.Disciplina;
import com.ufcg.sad.models.matricula.Matricula;
import com.ufcg.sad.models.professor.Professor;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Classe de teste para o model Aluno
 */
public class AlunoTest {

    @Test
    public void testaGetterESetter() {
        Aluno aluno = new Aluno();
        Long id = 2L;
        String nome = "Aluno da Silva";
        String email = "aluno@ccc.ufcg.edu.br";
        Matricula matricula = new Matricula(1L, aluno, geraDisciplina("Matemática"));
        Set<Matricula> matriculas = new HashSet<>();
        matriculas.add(matricula);

        aluno.setId(id);
        aluno.setNome(nome);
        aluno.setEmail(email);
        aluno.setDisciplinas(matriculas);

        Assert.assertEquals(id, aluno.getId());
        Assert.assertEquals(nome, aluno.getNome());
        Assert.assertEquals(email, aluno.getEmail());
        Assert.assertEquals(matriculas, aluno.getDisciplinas());
    }

    private Disciplina geraDisciplina(String nome) {
        Professor professor = new Professor();
        Disciplina matematica = new Disciplina(1L, nome, 1, professor, "20171", new HashSet<>());

        return matematica;
    }

}
