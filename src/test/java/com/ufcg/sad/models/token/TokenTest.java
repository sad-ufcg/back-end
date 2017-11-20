package com.ufcg.sad.models.token;

import com.ufcg.sad.SadApplicationTests;
import com.ufcg.sad.models.aluno.Aluno;
import com.ufcg.sad.models.disciplina.Disciplina;
import com.ufcg.sad.models.matricula.Matricula;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by sampaio on 16/11/17.
 */
public class TokenTest extends SadApplicationTests {

    private Token token1;
    private Token token2;

    private static final String ALUNO1 = "Aluno";
    private static final String ALUNO2 = "Aluno2";
    private static final String DISCIPLINA1 = "Disciplina";
    private static final String DISCIPLINA2 = "Disciplina2";



    @Before
    public void setUp() {

        token1 = new Token(createMatriculaTest(ALUNO1, DISCIPLINA1) );
        token2 = new Token(createMatriculaTest(ALUNO2, DISCIPLINA2));
    }


    @Test
    public void testaGetter(){
        Assert.assertNotEquals(token1.getId(),token2.getId());
        Assert.assertEquals(token1.getMatricula().getAluno().getNome(), ALUNO1);
    }


    public Matricula createMatriculaTest(String nomeAluno, String nomeDisciplina) {
        Aluno aluno = new Aluno();
        aluno.setNome(nomeAluno);
        Disciplina disciplina = new Disciplina();
        disciplina.setNome(nomeDisciplina);
        Matricula matricula = new Matricula(aluno, disciplina);

        return matricula;
    }
}
