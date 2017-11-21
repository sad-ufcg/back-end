package com.ufcg.sad.exceptions;

/**
 * Exceção lançada quando se tenta acessar uma entidade que não existe.
 * 
 * @author Antunes Dantas
 */
public class EntidadeNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public EntidadeNotFoundException() { super(); }

    public EntidadeNotFoundException(String mensagem) {
        super(mensagem);
    }
}
