package com.ufcg.sad.models.questionario;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.ufcg.sad.models.disciplina.Disciplina;
import com.ufcg.sad.models.professor.Professor;
import com.ufcg.sad.models.questao.Questao;
import com.ufcg.sad.models.questionario.Questionario;

/**
 * Classe de testes para questionário
 * 
 * @author Lucas Silva
 */
public class QuestionarioTest {
	/**
	 * Método que testa o construtor de questionário
	 */
	@Test
	public void testQuestionario() {
		Questionario questionario = new Questionario(new Long(1), "Questionário 1", new HashSet<Questao>());
		
		assertEquals(questionario.getNome(), "Questionário 1");
		assertEquals(questionario.getQuestoes(), new HashSet<Questao>());
		assertEquals(questionario.getQuestoes().size(), 0);
		
		questionario.setQuestoes(getQuestoes());
		
		assertEquals(questionario.getQuestoes().size(), 2);		
	}

	/**
	 * Método que gera um Set de questões para uso em testes
	 * @return Set com duas questões
	 */
	private Set<Questao> getQuestoes() {
		
		Set<Questao> questoes = new HashSet<Questao>();
		Professor autor = new Professor("siape", "Pedro", new HashSet<Disciplina>());
		Date dataCriacao = new Date();
		
		Questao questao1 = new Questao(new Long(1), "A ementa da disciplina foi seguida adequadamente?", autor, dataCriacao, "");
		Questao questao2 = new Questao(new Long(2), "Você tem alguma sugestão para melhorar a disciplina?", autor, dataCriacao, "");
		
		questoes.add(questao1);
		questoes.add(questao2);
		
		return questoes;
	}
}
