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
     */
    Questao criaQuestao(Questao questao) throws EntidadeInvalidaException;

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
	 */
    void validaQuestao(Questao questao) throws EntidadeInvalidaException;

    /**
     * Atualiza uma Questão no sistema
     *
     * @param aluno Questao a ser atualizada.
     *
     * @return Questao atualizada.
     * @throws EntidadeNotFoundException
     */
    Questao atualizaQuestao(Questao questao) throws EntidadeNotFoundException;

}
