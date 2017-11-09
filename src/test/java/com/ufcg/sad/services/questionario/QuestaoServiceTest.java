package com.ufcg.sad.services.questionario;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ufcg.sad.exceptions.questionario.QuestaoNaoExisteException;
import com.ufcg.sad.models.questionario.Questao;
import com.ufcg.sad.models.questionario.TipoResposta;

/**
 * Testes para o Serviço uma questão.
 * 
 * @author Marianne Linhares
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestaoServiceTest {

	@Autowired
	private QuestaoService questaoService;
	
	@Test
	public void testValidaQuestao() {

		// testando questoes invalidas
		Questao questaoVazia = new Questao();
		Questao questaoIdInvalido = new Questao(1L, "enunciado", TipoResposta.TEXTO, "sem comentario");
		Questao questaoEnunciadoNull = new Questao(null, null, TipoResposta.TEXTO, "sem comentario");
		Questao questaoEnunciadoVazio = new Questao(null, "", TipoResposta.TEXTO, "sem comentario");
		Questao questaoTipoNull = new Questao(null, "enunciado", null, "sem comentario");
		
		Assert.assertFalse(questaoService.validaQuestao(questaoVazia));
		Assert.assertFalse(questaoService.validaQuestao(questaoIdInvalido));
		Assert.assertFalse(questaoService.validaQuestao(questaoEnunciadoNull));
		Assert.assertFalse(questaoService.validaQuestao(questaoEnunciadoVazio));
		Assert.assertFalse(questaoService.validaQuestao(questaoTipoNull));
		
		// testando questao valida
		Questao questaoValida = new Questao(null, "enunciado", TipoResposta.TEXTO, "sem comentario");
		Assert.assertTrue(questaoService.validaQuestao(questaoValida));
	}
	
	@Test
	public void getQuestao() {
		// se nao existir questao com um certo id lanca excecao
		try {
			questaoService.getQuestao(2L);
			Assert.fail();
		}
		catch (QuestaoNaoExisteException exception) {
		
		}
		
		// se existir nao deve lancar excecao
		Questao questaoValida = new Questao(null, "enunciado", TipoResposta.TEXTO, "sem comentario");
		questaoService.criaQuestao(questaoValida);
		try {
			Assert.assertEquals(questaoValida, questaoService.getQuestao(2L));
		} catch (QuestaoNaoExisteException e) {
			Assert.fail();
		}
	}
	
	@Test
	public void getTodaQuestoes() {
		Questao questaoValida = new Questao(null, "enunciado", TipoResposta.TEXTO, "sem comentario");
		
		Assert.assertEquals(0, questaoService.getTodasQuestoes().size());
		
		questaoService.criaQuestao(questaoValida);
		questaoService.criaQuestao(questaoValida);
		questaoService.criaQuestao(questaoValida);
		
		List<Questao> todasQuestoes = questaoService.getTodasQuestoes();
		Assert.assertEquals(3, todasQuestoes.size());
		for(Questao q: todasQuestoes) {
			Assert.assertEquals(questaoValida, q);
		}
	}
}
