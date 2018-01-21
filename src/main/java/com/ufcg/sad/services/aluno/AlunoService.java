package com.ufcg.sad.services.aluno;

import com.ufcg.sad.exceptions.EntidadeInvalidaException;
import com.ufcg.sad.exceptions.EntidadeNotFoundException;
import com.ufcg.sad.models.aluno.Aluno;

import java.util.List;

/**
 * Interface que provê serviços para a entidade Aluno
 *
 * @author Antunes Dantas
 */
public interface AlunoService {

    /**
     * Cadastra um novo Aluno.
     *
     * @param aluno Aluno a ser cadastrado
     *
     * @throws EntidadeInvalidaException se aluno tiver dados inválidos.
     * @return Aluno cadastrado.
     */
    Aluno criarAluno(Aluno aluno) throws EntidadeInvalidaException;

    /**
     * Recupera um aluno através do Id
     *
     * @param id Id do aluno.
     *
     * @throws EntidadeNotFoundException se não achar o aluno lança essa exceção.
     * @return Aluno.
     */
    Aluno getAluno(Long id) throws EntidadeNotFoundException;

    /**
     * Recupera todos os Alunos cadastrados.
     *
     * @return Lista com as Alunos.
     */
    List<Aluno> getTodosOsAlunos();

    /**
     * Atualiza um Aluno no sistema
     *
     * @param aluno Aluno a ser atualizado.
     *
     * @throws EntidadeNotFoundException se não achar o aluno lança essa exceção.
     * @return Aluno atualizado.
     */
    Aluno atualizarAluno(Aluno aluno) throws EntidadeNotFoundException;

    /**
     * Remove um Aluno do sistema.
     *
     * @param aluno Aluno a ser removido.
     * 
     * @throws EntidadeNotFoundException se não achar o aluno lança essa exceção.
     */
    void removerAluno(Aluno aluno) throws EntidadeNotFoundException;

    /**
     * Procura por um Aluno utilizando um email.
     * @param email Email a ser procurado.
     * 
     * @throws EntidadeNotFoundException se não achar o aluno lança essa exceção.
     * @return Aluno.
     */
    Aluno procurarPorEmail(String email) throws EntidadeNotFoundException;
}
