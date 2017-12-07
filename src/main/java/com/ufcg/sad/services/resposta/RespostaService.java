package com.ufcg.sad.services.resposta;

import com.ufcg.sad.exceptions.EntidadeNotFoundException;
import com.ufcg.sad.models.resposta.Resposta;

import java.util.List;

/**
 * Interface que provê serviços para a entidade Resposta
 *
 * @author Arthur Vinícius
 */
public interface RespostaService {
	/**
     * Cadastra uma nova Resposta.
     *
     * @param resposta Resposta a ser cadastrada
     *
     * @return Resposta cadastrada.
     */
    Resposta criarResposta(Resposta resposta);

    /**
     * Recupera uma resposta através do Id
     *
     * @param id Id da resposta.
     *
     * @return Resposta.
     */
    Resposta getResposta(Long id) throws EntidadeNotFoundException;

    /**
     * Recupera todas as Respostas cadastradas.
     *
     * @return Lista com as Respostas.
     */
    List<Resposta> getTodasAsRespostas();
    
    /**
     * Adiciona uma lista de Respostas.
     * 
     * @return Lista de respostas adicionadas.
     */
    List<Resposta> addRespostas(List<Resposta> respostas);
}
