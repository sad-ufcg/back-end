package com.ufcg.sad.models.resposta;

import com.ufcg.sad.SadApplicationTests;
import com.ufcg.sad.models.opcao.Opcao;

import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Classe de teste para RespostaSelecao
 *
 * @author Arthur Costa
 */
public class RespostaSelecaoTest extends SadApplicationTests {

    @Test
    public void testaConstrutor() {
        Date dataCriacao = new Date();
        
        RespostaSelecao respostaSelecao= new RespostaSelecao(new Long(1), dataCriacao, new Long(1), new Long(1), new HashSet<Opcao>());

        assertEquals(respostaSelecao.getOpcoesSelecionadas().size(), 0);

        criaOpcoes(respostaSelecao);

        assertEquals(respostaSelecao.getOpcoesSelecionadas().size(), 2);
    }

    private void criaOpcoes(RespostaSelecao respostaSelecao) {
        
        Opcao opcao1 = new Opcao(new Long(1), "opcao 1", "primeira opcao");
        Opcao opcao2 = new Opcao(new Long(2), "opcao 2", "segunda opcao");

        Set<Opcao> opcoesAdd = new HashSet<Opcao>();

        opcoesAdd.add(opcao1);
        opcoesAdd.add(opcao2);

        respostaSelecao.setOpcoesSelecionadas(opcoesAdd);
    }
}
