package com.ufcg.sad.models.resposta;

import com.ufcg.sad.SadApplicationTests;
import com.ufcg.sad.models.disciplina.Disciplina;
import com.ufcg.sad.models.opcao.Opcao;
import com.ufcg.sad.models.professor.Professor;
import com.ufcg.sad.models.questao.Questao;
import com.ufcg.sad.models.questao.TipoQuestao;
import com.ufcg.sad.models.questionario.Questionario;
import com.ufcg.sad.models.questionario.QuestionarioAplicado;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Classe para teste de RespostaMultiplaEscolha.
 *
 * @author Arthur Costa
 */
public class RepostaMultiplaEscolhaTest extends SadApplicationTests {

    @Test
    public void testaConstrutor() {
        Professor autor = new Professor("siape", "Pedro", new HashSet<Disciplina>(), new QuestionarioAplicado());
        Date dataCriacao = new Date();
        List<Opcao> opcoes = new ArrayList<Opcao>();

        Questao questao = new Questao(new Long(1), "A ementa da disciplina foi seguida adequadamente?", autor, dataCriacao, dataCriacao, "", opcoes, TipoQuestao.ESCOLHA_SIMPLES, new Resposta());

        Professor professor = new Professor("siape", "João", new HashSet<Disciplina>(), null);
        Questionario questionario = new Questionario(new Long(1), "Questionario", "Uma descrição", new HashSet<Questao>(), professor, new Date(), new Date(), new HashSet<QuestionarioAplicado>());
       
        QuestionarioAplicado questionarioAplicado = new QuestionarioAplicado(new Long(1), questionario, professor, new Disciplina(), new HashSet<Resposta>());

        Opcao opcao1 = new Opcao(new Long(1), "opcao 1", "primeira opcao", questao, null, null);
        Opcao opcao2 = new Opcao(new Long(2), "opcao 2", "segunda opcao", questao, null, null);
        
        RespostaMultiplaEscolha respostaMultiplaEscolha = new RespostaMultiplaEscolha(dataCriacao, questao, questionarioAplicado, opcao1, "um comentario aqui");
 
        assertEquals(respostaMultiplaEscolha.getOpcaoEscolhida(), opcao1);
        assertEquals(respostaMultiplaEscolha.getComentario(), "um comentario aqui");

        respostaMultiplaEscolha.setOpcaoEscolhida(opcao2);
        respostaMultiplaEscolha.setComentario("um outro comentario aqui");

        assertEquals(respostaMultiplaEscolha.getOpcaoEscolhida(), opcao2);
        assertEquals(respostaMultiplaEscolha.getComentario(), "um outro comentario aqui");

    }
}
