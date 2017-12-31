package com.ufcg.sad.models.resposta;

import com.ufcg.sad.SadApplicationTests;
import com.ufcg.sad.models.opcao.Opcao;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Classe para teste de RespostaMultiplaEscolha.
 *
 * @author Arthur Costa
 */
public class RepostaMultiplaEscolhaTest extends SadApplicationTests {

    @Test
    public void testaConstrutor() {
        Date dataCriacao = new Date();

        Opcao opcao1 = new Opcao(new Long(1), "opcao 1", "primeira opcao");
        Opcao opcao2 = new Opcao(new Long(2), "opcao 2", "segunda opcao");

        RespostaMultiplaEscolha respostaMultiplaEscolha = new RespostaMultiplaEscolha(new Long(1), dataCriacao, new Long(1), new Long(1), opcao1, "um comentario aqui");

        assertEquals(respostaMultiplaEscolha.getOpcaoEscolhida(), opcao1);
        assertEquals(respostaMultiplaEscolha.getComentario(), "um comentario aqui");

        respostaMultiplaEscolha.setOpcaoEscolhida(opcao2);
        respostaMultiplaEscolha.setComentario("um outro comentario aqui");

        assertEquals(respostaMultiplaEscolha.getOpcaoEscolhida(), opcao2);
        assertEquals(respostaMultiplaEscolha.getComentario(), "um outro comentario aqui");

    }
}
