package com.ufcg.sad.models.token;

import com.ufcg.sad.SadApplicationTests;
import com.ufcg.sad.models.disciplina.Disciplina;
import com.ufcg.sad.models.professor.Professor;
import com.ufcg.sad.models.questao.Questao;
import com.ufcg.sad.models.questionario.Questionario;
import com.ufcg.sad.models.questionario.QuestionarioAplicado;
import com.ufcg.sad.models.resposta.Resposta;

import java.util.Date;
import java.util.HashSet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by sampaio on 16/11/17.
 */
public class TokenTest extends SadApplicationTests {

	private Token token1;
	private Token token2;
	
	private QuestionarioAplicado questionarioAplicado;
	
    @Before
    public void setUp() {

    	Professor professor = new Professor("siape", "João", new HashSet<Disciplina>(), null);
    	Questionario questionario = new Questionario(new Long(1), "Questionario", "", new HashSet<Questao>(), professor, new Date(), new Date(), new HashSet<QuestionarioAplicado>());
	
    	questionarioAplicado = new QuestionarioAplicado(new Long(1), questionario, professor, new Disciplina(), new HashSet<Resposta>());
    	
        token1 = new Token(questionarioAplicado);
        token2 = new Token(questionarioAplicado);
    }


    @Test
    public void testaGetter(){
        Assert.assertNotEquals(token1.getId(),token2.getId());
        Assert.assertEquals(questionarioAplicado, token1.getQuestionarioAplicado());
    }

}
