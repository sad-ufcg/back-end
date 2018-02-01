package com.ufcg.sad.services.questionario;

import com.ufcg.sad.exceptions.EntidadeInvalidaException;
import com.ufcg.sad.exceptions.EntidadeNotFoundException;
import com.ufcg.sad.exceptions.ParametroInvalidoException;
import com.ufcg.sad.models.questionario.QuestionarioAplicado;

import java.util.List;

/**
 * Interface que provê serviços para a entidade QuestionarioAplicado.
 * 
 * @author Arthur Vinícius
 */
public interface QuestionarioAplicadoService {
	/**
     * Cria um novo questionário aplicado.
     *
     * @param questionarioAplicado QuestionarioAplicado a ser cadastrado.
     *
     * @return Questionário aplicado criado.
	 * @throws EntidadeNotFoundException 
	 * @throws EntidadeInvalidaException 
     */
	QuestionarioAplicado criaQuestionarioAplicado(QuestionarioAplicado questionarioAplicado) throws ParametroInvalidoException, EntidadeInvalidaException, EntidadeNotFoundException;

    /**
     * Recupera um questionário aplicado através do Id.
     *
     * @param id Id do questionário aplicado.
     *
     * @return Questionário aplicado.
     */
	QuestionarioAplicado getQuestionarioAplicado(Long id) throws EntidadeNotFoundException;

    /**
     * Recupera todos os questionários aplicados.
     *
     * @return Lista com os questionários aplicados.
     */
    List<QuestionarioAplicado> getTodosQuestionariosAplicados();

    /**
     * Atualiza um questionário aplicado no sistema.
     *
     * @param questionarioAplicado QuestionarioAplicado a ser atualizado.
     *
     * @return Questionário aplicado atualizado.
     * @throws EntidadeInvalidaException 
     */
    QuestionarioAplicado atualizaQuestionarioAplicado(QuestionarioAplicado questionarioAplicado) throws EntidadeNotFoundException, EntidadeInvalidaException;

    /**
     * Recupera uma lista de Questionários Aplicados.
     *
     * @param ids
     *          Lista com os ids dos Questionários Aplicados desejados.
     *
     * @return
     *          Lista com os Questionários Aplicados.
     */
    List<QuestionarioAplicado> getListaDeQuestionariosAplicados(List<Long> ids);
    
    /**
     * Recupara um Set de Questionários Aplicados que são de um certo Questionário.
     * @param id do questionário.
     */
	List<QuestionarioAplicado> getQuestionarioAplicados(Long id);
}
