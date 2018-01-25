package com.ufcg.sad.services.questionario;

import com.ufcg.sad.exceptions.EntidadeInvalidaException;
import com.ufcg.sad.exceptions.EntidadeNotFoundException;

import java.util.List;

/**
 * Realiza o envio de um questionário
 */
public interface EnviarQuestionarioService {

    /**
     * Envia um email para um Questionário Aplicado.
     * @param idQuestionarioAplicado
     *          Questionario que será utilizado para enviar os emails
     * @throws EntidadeNotFoundException, EntidadeInvalidaException 
     */
    public void enviarEmail(Long idQuestionarioAplicado) throws EntidadeNotFoundException, EntidadeInvalidaException;

    /**
     * Envia email para um conjunto de questionários aplicados.
     *
     * @param questionariosAplicados
     *          Lista com os questionários a serem enviados
     * @throws EntidadeInvalidaException, EntidadeNotFoundException 
     */
    public void enviarEmail(List<Long> questionariosAplicados) throws EntidadeInvalidaException, EntidadeNotFoundException;
}
