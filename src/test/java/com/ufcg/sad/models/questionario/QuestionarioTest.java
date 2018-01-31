package com.ufcg.sad.models.questionario;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import com.ufcg.sad.SadApplicationTests;
import org.junit.Test;

import com.ufcg.sad.models.disciplina.Disciplina;
import com.ufcg.sad.models.opcao.Opcao;
import com.ufcg.sad.models.professor.Professor;
import com.ufcg.sad.models.questao.Questao;
import com.ufcg.sad.models.questao.TipoQuestao;
import com.ufcg.sad.models.questionario.Questionario;

/**
 * Classe de testes para questionário
 * 
 * @author Lucas Silva
 */
public class QuestionarioTest extends SadApplicationTests {
	/**
	 * Método que testa o construtor de questionário
	 */
	@Test
	public void testQuestionario() {

		Professor autor = new Professor("siape", "Pedro", new HashSet<Disciplina>());
		Date dataCriacao = new Date();

		Questionario questionario = new Questionario(new Long(1), "Questionário 1", "", new ArrayList<Questao>(), autor, dataCriacao, dataCriacao);

		assertEquals(questionario.getNome(), "Questionário 1");
		assertEquals(questionario.getDescricao(), "");
		assertEquals(questionario.getQuestoes(), new HashSet<Questao>());
		assertEquals(questionario.getQuestoes().size(), 0);

		questionario.setQuestoes(getQuestoes());

		assertEquals(questionario.getQuestoes().size(), 2);

		assertEquals(autor, questionario.getAutor());

		assertEquals(dataCriacao, questionario.getDataCriacao());
		assertEquals(dataCriacao, questionario.getDataUltimaEdicao());
	}

	/**
	 * Método que gera um Set de questões para uso em testes
	 * @return Set com duas questões
	 */
	private List<Questao> getQuestoes() {
		
		List<Questao> questoes = new ArrayList<Questao>();
		
		Professor autor = new Professor("siape", "Pedro", new HashSet<Disciplina>());
		Date dataCriacao = new Date();
		List<Opcao> opcoes = new ArrayList<Opcao>();
		
		Questao questao1 = new Questao(new Long(1), "A ementa da disciplina foi seguida adequadamente?", autor, dataCriacao, dataCriacao, "", opcoes, TipoQuestao.ESCOLHA_SIMPLES);
		Questao questao2 = new Questao(new Long(2), "A ementa da disciplina foi seguida adequadamente?", autor, dataCriacao, dataCriacao, "", opcoes, TipoQuestao.ESCOLHA_SIMPLES);
		
		questoes.add(questao1);
		questoes.add(questao2);
		
		return questoes;
	}
}
