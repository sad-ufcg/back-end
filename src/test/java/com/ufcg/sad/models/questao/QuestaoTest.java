package com.ufcg.sad.models.questao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.junit.Test;

import com.ufcg.sad.SadApplicationTests;
import com.ufcg.sad.models.disciplina.Disciplina;
import com.ufcg.sad.models.opcao.Opcao;
import com.ufcg.sad.models.professor.Professor;
import com.ufcg.sad.models.questao.Questao;
import com.ufcg.sad.models.questao.TipoQuestao;

/**
 * Classe de testes para Questão.
 * 
 * @author Lucas Silva, Marianne Linhares
 */
public class QuestaoTest extends SadApplicationTests {
	/**
	 * Método que testa o construtor de questão
	 */
	@Test
	public void testQuestao() {
		
		Professor autor = new Professor("siape", "Pedro", new HashSet<Disciplina>());
		Date dataCriacao = new Date();
		List<Opcao> opcoes = new ArrayList<Opcao>();
		
		Questao questao = new Questao(new Long(1), "A ementa da disciplina foi seguida adequadamente?", autor, dataCriacao, dataCriacao, "", opcoes, TipoQuestao.ESCOLHA_SIMPLES);
		
		assertEquals(questao.getId(), new Long(1));
		assertEquals(questao.getEnunciado(), "A ementa da disciplina foi seguida adequadamente?");

		assertEquals(questao.getAutor(), autor);
		assertEquals(questao.getDataCriacao(), dataCriacao);
		assertEquals(questao.getDataUltimaEdicao() , dataCriacao);
		assertEquals(questao.getComentario(), "");
		assertEquals(questao.getTipoQuestao(), TipoQuestao.ESCOLHA_SIMPLES);
		assertEquals(questao.getOpcoes(), opcoes);
	}
}
