package com.ufcg.sad.models.questionario;

import com.ufcg.sad.SadApplicationTests;
import com.ufcg.sad.models.resposta.Resposta;
import com.ufcg.sad.models.resposta.RespostaAberta;
import com.ufcg.sad.models.token.Token;

import org.junit.Test;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Classe para testar QuestionarioAplicado.
 *
 * @author Arthur Costa
 */
public class QuestionarioAplicadoTest extends SadApplicationTests {

    @Test
    public void testaConstrucao() {
        QuestionarioAplicado questionarioAplicado = new QuestionarioAplicado(null, new Long(1), new Long(1), new Long(1), new HashSet<Resposta>(), new HashSet<Token>());

        assertEquals(questionarioAplicado.getIdProfessor(), new Long(1));
        assertEquals(questionarioAplicado.getIdQuestionario(), new Long(1));
        assertEquals(questionarioAplicado.getRespostas().size(), 0);

        adicionaRespostas(questionarioAplicado);

        assertEquals(questionarioAplicado.getRespostas().size(), 2);
    }

    private void adicionaRespostas(QuestionarioAplicado questionarioAplicado) {

        Date dataCriacao = new Date();
        
        Resposta resposta1 = new RespostaAberta(new Long(1), dataCriacao, new Long(1), new Long(1), "aqui vai um comentario");
        Resposta resposta2 = new RespostaAberta(new Long(2), dataCriacao, new Long(1), new Long(1), "aqui vai um outro comentario");

        Set<Resposta> respostas = new HashSet<Resposta>();

        respostas.add(resposta1);
        respostas.add(resposta2);

        questionarioAplicado.setRespostas(respostas);
    }
}
