package com.ufcg.sad.services.questao;

import com.ufcg.sad.exceptions.EntidadeInvalidaException;
import com.ufcg.sad.exceptions.EntidadeNotFoundException;
import com.ufcg.sad.models.questao.Questao;

import java.util.List;

/**
 * Interface que provê serviços para a entidade Questão.
 * 
 * @author Marianne Linhares
 */
public interface QuestaoService {

    /**
     * Cria um novo Questão.
     *
     * @param questao Questao a ser criada
     *
     * @return Questao
     * @throws EntidadeInvalidaException 
     * @throws EntidadeNotFoundException 
     */
    Questao criaQuestao(Questao questao) throws EntidadeInvalidaException, EntidadeNotFoundException;

    /**
     * Recupera um questão através do Id
     *
     * @param id Id de questão.
     *
     * @return Questao.
     */
    Questao getQuestao(Long id) throws EntidadeNotFoundException;

    /**
     * Recupera todos os Questões.
     *
     * @return Lista com as questoes.
     */
    List<Questao> getTodasQuestoes();
    
	
	/**
	 * Método auxiliar para validar uma questão.
	 * @param questao
	 * @throws EntidadeInvalidaException 
	 * @throws EntidadeNotFoundException 
	 */
    void validaQuestao(Questao questao) throws EntidadeInvalidaException, EntidadeNotFoundException;

    /**
     * Atualiza uma Questão no sistema
     *
     * @param aluno Questao a ser atualizada.
     *
     * @return Questao atualizada.
     * @throws EntidadeNotFoundException
     * @throws EntidadeInvalidaException 
     */
    Questao atualizaQuestao(Questao questao) throws EntidadeNotFoundException, EntidadeInvalidaException;

}
