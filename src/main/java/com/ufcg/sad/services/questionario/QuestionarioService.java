package com.ufcg.sad.services.questionario;

import com.ufcg.sad.exceptions.EntidadeInvalidaException;
import com.ufcg.sad.exceptions.EntidadeNotFoundException;
import com.ufcg.sad.exceptions.ParametroInvalidoException;
import com.ufcg.sad.models.disciplina.Disciplina;
import com.ufcg.sad.models.questionario.Questionario;
import com.ufcg.sad.models.questionario.QuestionarioAplicado;

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
     * @throws EntidadeInvalidaException, ParametroInvalidoException 
     * @throws EntidadeNotFoundException 
     */
    Questionario criaQuestionario(Questionario questionario) throws ParametroInvalidoException, EntidadeInvalidaException, EntidadeNotFoundException;

    /**
     * Recupera um questionário através do Id
     *
     * @param id Id do questionário.
     *
     * @return Questionario
     * @throws EntidadeInvalidaException
     */
    Questionario getQuestionario(Long id) throws EntidadeNotFoundException;

    /**
     * Recupera todos os Questionários.
     *
     * @return Lista com os questionários
     */
    List<Questionario> getTodosQuestionarios();

    /**
     * Atualiza um Questionário no sistema
     *
     * @param questionario Questionário a ser atualizado.
     *
     * @return Questionário atualizado
     * @throws EntidadeInvalidaException
     */
    Questionario atualizaQuestionario(Questionario questionario) throws EntidadeNotFoundException, EntidadeInvalidaException;

    /**
     * Busca questionarios aplicados de um questionario.
     * @param id id do Questionário
     * @param idDisciplina 
     * @param semestre 
     * @return Lista de QuestionariosAplicados
     */
	List<QuestionarioAplicado> getQuestionariosAplicados(Long id, String semestre, Long idDisciplina);
	 
	/**
     * Busca disciplinas de um questionário associadas por meio de um
     * questionário aplicado.
     * @param id id do Questionário
     * @return Lista de Discilpinas
	 * @throws EntidadeNotFoundException 
     */
	List<Disciplina> getDisciplinas(Long id, String semestre) throws EntidadeNotFoundException;
}
