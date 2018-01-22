package com.ufcg.sad.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção lançada quando uma entidade não válida é detectada na REST.
 * 
 * @author Marianne Linhares
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EntidadeInvalidaException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public EntidadeInvalidaException() {
		super();
	}

    public EntidadeInvalidaException(String mensagem) {
        super(mensagem);
    }

}
