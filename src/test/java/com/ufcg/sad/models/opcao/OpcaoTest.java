package com.ufcg.sad.models.opcao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import com.ufcg.sad.models.questionario.QuestionarioAplicado;
import com.ufcg.sad.models.resposta.Resposta;
import com.ufcg.sad.models.resposta.RespostaMultiplaEscolha;
import com.ufcg.sad.models.resposta.RespostaSelecao;
import org.junit.Test;

import com.ufcg.sad.SadApplicationTests;
import com.ufcg.sad.models.disciplina.Disciplina;
import com.ufcg.sad.models.professor.Professor;
import com.ufcg.sad.models.questao.Questao;
import com.ufcg.sad.models.questao.TipoQuestao;

/**
 * Classe de testes para Opção
 * 
 * @author Marianne Linhares
 */
public class OpcaoTest extends SadApplicationTests {

	/**
	 * Método que testa o construtor de opcão.
	 */
	@Test
	public void testQuestao() {	
		Professor autor = new Professor("siape", "Pedro", new HashSet<Disciplina>(), new QuestionarioAplicado());
		Date dataCriacao = new Date();
		List<Opcao> opcoes = new ArrayList<Opcao>();
		
		Questao questao = new Questao(new Long(1), "A ementa da disciplina foi seguida adequadamente?", autor, dataCriacao, dataCriacao, "", opcoes, TipoQuestao.ESCOLHA_SIMPLES, new Resposta());
		
		RespostaSelecao respostaSelecao = new RespostaSelecao();
		RespostaMultiplaEscolha respostaME = new RespostaMultiplaEscolha();
		
		Opcao opcao = new Opcao(new Long(1), "opcao 1", "primeira opcao", questao, respostaME, respostaSelecao);
		
		assertEquals(new Long(1), opcao.getId());
		assertEquals("opcao 1", opcao.getNome());
		assertEquals("primeira opcao", opcao.getDescricao());
		assertEquals(questao, opcao.getQuestao());

		Opcao opcao2 = new Opcao(new Long(1), "opcao 1", "primeira opcao", questao, new RespostaMultiplaEscolha());

		assertEquals(opcao2.getRespostaMultiplaEscolha(), new RespostaMultiplaEscolha());
		assertTrue(opcao2.getRespostaSelecao() == null);
	}

}
