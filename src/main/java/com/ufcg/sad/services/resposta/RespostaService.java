package com.ufcg.sad.services.resposta;

import com.ufcg.sad.exceptions.EntidadeInvalidaException;
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
     * @param token, resposta Resposta a ser cadastrada
     *
     * @return Resposta cadastrada.
	 * @throws EntidadeInvalidaException 
	 * @throws EntidadeNotFoundException.
     */
    Resposta criarResposta(Resposta resposta) throws EntidadeNotFoundException, EntidadeInvalidaException;

    /**
     * Recupera uma resposta através do Id
     *
     * @param id Id da resposta.
     *
     * @return Resposta.
     */
    Resposta getResposta(Long id) throws EntidadeNotFoundException;

    /**
     * Recupera todas as Respostas cadastradas de um certo tipo e de um certo idQuestao.
     * @param tipoResposta, idQuestao.
     *
     * @return Lista com as Respostas.
     */
    List<Resposta> getTodasAsRespostas(String tipoResposta, Long idQuestao);
 
    /**
     * Adiciona uma lista de Respostas.
     * @param token, respostas
     * 
     * @return Lista de respostas adicionadas.
     * @throws Exception.
     */
    List<Resposta> addRespostas(String token, List<Resposta> respostas) throws EntidadeNotFoundException, Exception;

}
