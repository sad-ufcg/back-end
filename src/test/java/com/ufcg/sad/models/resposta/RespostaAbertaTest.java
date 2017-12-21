package com.ufcg.sad.models.resposta;

import com.ufcg.sad.SadApplicationTests;
import com.ufcg.sad.models.disciplina.Disciplina;
import com.ufcg.sad.models.opcao.Opcao;
import com.ufcg.sad.models.professor.Professor;
import com.ufcg.sad.models.questao.Questao;
import com.ufcg.sad.models.questao.TipoQuestao;
import com.ufcg.sad.models.questionario.QuestionarioAplicado;
import com.ufcg.sad.models.token.Token;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Classe de teste para RespostaAberta.
 *
 * @author Arthur Costa
 */
public class RespostaAbertaTest extends SadApplicationTests {

    @Test
    public void testaConstrutor() {

        Professor autor = new Professor("siape", "Pedro", new HashSet<Disciplina>(), new QuestionarioAplicado());
        Date dataCriacao = new Date();
        List<Opcao> opcoes = new ArrayList<Opcao>();

        Questao questao = new Questao(new Long(1), "A ementa da disciplina foi seguida adequadamente?", autor, dataCriacao, dataCriacao, "", opcoes, TipoQuestao.ESCOLHA_SIMPLES, new Resposta());
        QuestionarioAplicado questionarioAplicado = new QuestionarioAplicado(null, new Long(1), new Long(1), new Long(1), new HashSet<Resposta>(), new HashSet<Token>());

        RespostaAberta resposta = new RespostaAberta(dataCriacao, questao, questionarioAplicado, "um comentario aqui");

        assertEquals(resposta.getComentario(), "um comentario aqui");
    }
}
