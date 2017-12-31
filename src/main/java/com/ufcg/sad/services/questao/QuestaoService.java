package com.ufcg.sad.services.questao;

import com.ufcg.sad.exceptions.EntidadeNotFoundException;
import com.ufcg.sad.exceptions.questionario.QuestaoInvalidaException;
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
     */
    Questao criaQuestao(Questao questao) throws QuestaoInvalidaException;

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
	 * @return boolean
	 */
    boolean validaQuestao(Questao questao);

    /**
     * Atualiza uma Questão no sistema
     *
     * @param aluno Questao a ser atualizada.
     *
     * @return Questao atualizada.
     */
    Questao atualizaQuestao(Questao questao) throws EntidadeNotFoundException, QuestaoInvalidaException;

}
