package com.ufcg.sad.models.resposta;

import com.ufcg.sad.SadApplicationTests;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Classe de teste para RespostaEscolhaSimples.
 *
 * @author Marianne Linhares
 */
public class RespostaEscolhaSimplesTest extends SadApplicationTests {

    @Test
    public void testaConstrutor() {
    	Date dataCriacao = new Date();
        
    	RespostaEscolhaSimples resposta = new RespostaEscolhaSimples(new Long(1), dataCriacao, new Long(1), new Long(1), 2, "um comentario aqui");

        assertEquals("um comentario aqui", resposta.getComentario());
        assertEquals(new Integer(2), resposta.getEscolhaSimples());
    }
}
