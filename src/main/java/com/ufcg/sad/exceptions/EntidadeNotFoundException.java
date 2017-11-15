package com.ufcg.sad.exceptions;

public class EntidadeNotFoundException extends Exception {

    public EntidadeNotFoundException() { super(); }

    public EntidadeNotFoundException(String mensagem) {
        super(mensagem);
    }
}
