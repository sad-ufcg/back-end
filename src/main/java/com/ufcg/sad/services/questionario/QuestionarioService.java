package com.ufcg.sad.services.questionario;

import com.ufcg.sad.exceptions.EntidadeNotFoundException;
import com.ufcg.sad.exceptions.questionario.QuestaoInvalidaException;
import com.ufcg.sad.exceptions.questionario.QuestionarioSemNomeException;
import com.ufcg.sad.exceptions.questionario.QuestionarioVazioException;
import com.ufcg.sad.exceptions.utils.ParametroInvalidoException;
import com.ufcg.sad.models.questionario.Questionario;

import java.util.List;

/**
 * Interface que provê serviços para a entidade Questionário.
 * 
 * @author Marianne Linhares
 */
public interface QuestionarioService {

    /**
     * Cria um novo Questionário.
     *
     * @param questionario Questionario a ser cadastrado
     *
     * @return Questionario
     */
    Questionario criaQuestionario(Questionario questionario) throws QuestionarioVazioException, QuestionarioSemNomeException, QuestaoInvalidaException, ParametroInvalidoException;

    /**
     * Recupera um questionário através do Id
     *
     * @param id Id do questionário.
     *
     * @return Questionario.
     */
    Questionario getQuestionario(Long id) throws EntidadeNotFoundException;

    /**
     * Recupera todos os Questionários.
     *
     * @return Lista com os questionários.
     */
    List<Questionario> getTodosQuestionarios();

    /**
     * Atualiza um Questionário no sistema
     *
     * @param aluno Questionário a ser atualizado.
     *
     * @return Questionário atualizado.
     */
    Questionario atualizaQuestionario(Questionario questionario) throws EntidadeNotFoundException, QuestaoInvalidaException;
}
