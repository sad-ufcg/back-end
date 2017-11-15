package com.ufcg.sad.services.email;

/**
 * Serviço de envio de e-mail
 */
public interface EmailService {

    /**
     * Envia um email para um único destinatário
     *
     * @param destinatario Email do destinatário
     * @param assunto Assunto do Email
     * @param corpo Corpo do Email
     */
    public void enviarEmail(String destinatario, String assunto, String corpo);

}
