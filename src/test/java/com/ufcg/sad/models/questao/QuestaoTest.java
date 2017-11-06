package com.ufcg.sad.models.questao;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ufcg.sad.models.Questao;
import com.ufcg.sad.models.TipoResposta;

public class QuestaoTest {
	@Test
	public void testQuestao() {
		Questao questao = new Questao(new Long(1), "A ementa da disciplina foi seguida adequadamente?", TipoResposta.ESCOLHA_SIMPLES, "");
		
		assertEquals(questao.getId(), new Long(1));
		assertEquals(questao.getComentario(), "");
		assertEquals(questao.getEnunciado(), "A ementa da disciplina foi seguida adequadamente?");
		assertEquals(questao.getTipoResposta(), TipoResposta.ESCOLHA_SIMPLES);
	}
}
