package com.ufcg.sad.models.opcao;

import static org.junit.Assert.assertEquals;


import org.junit.Test;

import com.ufcg.sad.SadApplicationTests;

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
	public void testOpcao() {	
		Opcao opcao = new Opcao(new Long(1), "opcao 1", "primeira opcao");
		
		assertEquals(new Long(1), opcao.getId());
		assertEquals("opcao 1", opcao.getNome());
		assertEquals("primeira opcao", opcao.getDescricao());
	}

}
