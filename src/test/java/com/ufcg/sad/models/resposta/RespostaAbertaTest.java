package com.ufcg.sad.models.resposta;

import com.ufcg.sad.SadApplicationTests;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Classe de teste para RespostaAberta.
 *
 * @author Arthur Costa
 */
public class RespostaAbertaTest extends SadApplicationTests {

    @Test
    public void testaConstrutor() {
    	Date dataCriacao = new Date();
        
    	RespostaAberta resposta = new RespostaAberta(new Long(1), dataCriacao, new Long(1), new Long(1), "um comentario aqui");

        assertEquals(resposta.getComentario(), "um comentario aqui");
    }
}
