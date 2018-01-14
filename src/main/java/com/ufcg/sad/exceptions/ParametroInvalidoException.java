package com.ufcg.sad.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção lançada quando uma requisão utiliza algum parâmetro inválido.
 * 
 * @author Marianne Linhares
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ParametroInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ParametroInvalidoException() {
		super();
	}

    public ParametroInvalidoException(String mensagem) {
        super(mensagem);
    }
}
