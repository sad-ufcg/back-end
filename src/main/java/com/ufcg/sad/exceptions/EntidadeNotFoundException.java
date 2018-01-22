package com.ufcg.sad.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção lançada quando se tenta acessar uma entidade que não existe.
 * 
 * @author Antunes Dantas
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntidadeNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public EntidadeNotFoundException() {
		super();
	}

    public EntidadeNotFoundException(String mensagem) {
        super(mensagem);
    }
}
