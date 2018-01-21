package com.ufcg.sad.services.email;

import javax.mail.MessagingException;

/**
 * Serviços de envio de email
 */
public interface EmailService {

    /**
     * Envia um email para o destinatário passado como parâmetro
     * @param destinatario
     *          Destinatário do email
     * @param corpo
     *          Corpo do email
     */
    public void enviarEmail(String destinatario, String corpo) throws MessagingException;
}
