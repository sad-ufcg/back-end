package com.ufcg.sad.services.questionario;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
/**
 * Testes para o serviço de um questionário.
 * 
 * @author Marianne Linhares
 */
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ufcg.sad.exceptions.questionario.QuestaoInvalidaException;
import com.ufcg.sad.exceptions.questionario.QuestionarioNaoExisteException;
import com.ufcg.sad.exceptions.questionario.QuestionarioSemNomeException;
import com.ufcg.sad.exceptions.questionario.QuestionarioVazioException;
import com.ufcg.sad.exceptions.utils.ParametroInvalidoException;
import com.ufcg.sad.models.questionario.Questao;
import com.ufcg.sad.models.questionario.Questionario;
import com.ufcg.sad.models.questionario.TipoResposta;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionarioServiceTest {
	
	@Autowired
	private QuestionarioService questionarioService;
	
	private Set<Questao> setQuestoes;
	
	@Before
	public void criarService() {
		
		// set de questoes valido
		setQuestoes = new HashSet<Questao>();
		
		Questao questaoValida1 = new Questao(1L, "enunciado", TipoResposta.TEXTO, "sem comentario");
		Questao questaoValida2 = new Questao(2L, "enunciado", TipoResposta.TEXTO, "sem comentario");
		Questao questaoValida3 = new Questao(3L, "enunciado", TipoResposta.TEXTO, "sem comentario");
		
		setQuestoes.add(questaoValida1);
		setQuestoes.add(questaoValida2);
		setQuestoes.add(questaoValida3);
	}
	
	@Test
	public void getQuestionario() {
		// se nao existir questao com um certo id lanca excecao
		try {
			questionarioService.getQuestionario(1L);
			Assert.fail();
		}
		catch (QuestionarioNaoExisteException exception) {
		
		}
		
		// se existir nao deve lancar excecao
		Questionario questionarioValido = new Questionario(1L, "questionario", setQuestoes);
		try {
			questionarioService.criaQuestionario(questionarioValido);
		} catch (QuestionarioVazioException | QuestionarioSemNomeException | QuestaoInvalidaException
				| ParametroInvalidoException e1) {
			Assert.fail();
		}
		try {
			Assert.assertEquals(questionarioValido, questionarioService.getQuestionario(1L));
		} catch (QuestionarioNaoExisteException e) {
			Assert.fail();
		}
	}
	

}

