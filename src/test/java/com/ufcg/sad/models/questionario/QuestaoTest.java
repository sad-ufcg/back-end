package com.ufcg.sad.models.questionario;


import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.HashSet;

import org.junit.Test;

import com.ufcg.sad.models.disciplina.Disciplina;
import com.ufcg.sad.models.professor.Professor;
import com.ufcg.sad.models.questao.Questao;

/**
 * Classe de testes para Questão
 * 
 * @author Lucas Silva
 */
public class QuestaoTest {
	/**
	 * Método que testa o construtor de questão
	 */
	@Test
	public void testQuestao() {
		Professor autor = new Professor("siape", "Pedro", new HashSet<Disciplina>());
		Date dataCriacao = new Date();
		Questao questao = new Questao(new Long(1), "A ementa da disciplina foi seguida adequadamente?", autor, dataCriacao, "");
		
		assertEquals(questao.getId(), new Long(1));
		assertEquals(questao.getEnunciado(), "A ementa da disciplina foi seguida adequadamente?");

		assertEquals(questao.getAutor(), autor);
		assertEquals(questao.getDataCriacao(), dataCriacao);
		assertEquals(questao.getDataUltimaEdicao() , dataCriacao);
		assertEquals(questao.getComentario(), "");
	}
}
