package com.ufcg.sad.services.disciplina;

import com.ufcg.sad.exceptions.EntidadeInvalidaException;
import com.ufcg.sad.exceptions.EntidadeNotFoundException;
import com.ufcg.sad.models.aluno.Aluno;
import com.ufcg.sad.models.disciplina.Disciplina;

import java.util.List;

/**
 * Serviços relacionados a entidade disciplina.
 *
 * @author Antunes Dantas
 */
public interface DisciplinaService {

	/**
     * Valida uma disciplina.
     *
     * @param disciplina disciplina a ser validada.
     *
     * @return disciplina.
     * @throws EntidadeNotFoundException Lança exceção se o professor não existir.
     * @throws EntidadeInvalidaException Lança exceção se a disciplina for inválida.
     */
    void validaDisciplina(Disciplina disciplina) throws EntidadeNotFoundException, EntidadeInvalidaException;

	
    /**
     * Cadastra uma disciplina no sistema
     *
     * @param disciplina disciplina a ser adicionada no sistema.
     *
     * @return disciplina cadastrada.
     * @throws EntidadeNotFoundException Lança exceção se o professor passado não existir.
     * @throws EntidadeInvalidaException Lança exceção se o aluno não for criado corretamente.
     */
    Disciplina cadastrarDisciplina(Disciplina disciplina) throws EntidadeNotFoundException, EntidadeInvalidaException;

    /**
     * Recupera uma disciplina através do Id
     *
     * @param id Id da disciplina.
     *
     * @return disciplina.
     * @throws EntidadeNotFoundException Lança exceção se a disciplina não existir.
     */
    Disciplina getDisciplina(Long id) throws EntidadeNotFoundException;

    /**
     * Recupera todas as Disciplinas cadastradas.
     *
     * @return Lista com as Disciplinas.
     */
    List<Disciplina> listarTodasAsDisciplinas();

    /**
     * Atualiza uma disciplina no sistema
     *
     * @param disciplina disciplina atualizada.
     *
     * @return Discioplina atualizada.
     * @throws EntidadeInvalidaException Lança exceção se o aluno não for criado corretamente.
     * @throws EntidadeNotFoundException Lança exceção se a disciplina não existir.
     */
    Disciplina atualizarDisciplina(Disciplina disciplina) throws EntidadeNotFoundException, EntidadeInvalidaException;

    /**
     * Remove uma discilina do sistema.
     *
     * @param disciplina disciplina a ser removida.
     * @throws EntidadeNotFoundException Lança exceção se a disciplina não existir.
     */
    void removerDisciplina(Disciplina disciplina) throws EntidadeNotFoundException;

    /**
     * Adiciona um novo Aluno a uma Disciplina já existente.
     *
     * @param aluno Aluno a ser adicionado.
     * @param idDisciplina Disciplina a qual o Aluno será vinculado.
     *
     * @return Disciplina atualizada.
     *
     * @throws EntidadeNotFoundException Lança exceção se a turma passada não existir.
     * @throws EntidadeInvalidaException Lança exceção se o aluno não for criado corretamente.
     */
    Aluno vincularAluno(Long idDisciplina, Aluno aluno) throws EntidadeNotFoundException, EntidadeInvalidaException;
}
