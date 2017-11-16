package com.ufcg.sad.services.aluno;

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
     * @return Aluno cadastrado.
     */
    Aluno criarAluno(Aluno aluno);

    /**
     * Recupera um aluno através do Id
     *
     * @param id Id do aluno.
     *
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
     * @return Aluno atualizado.
     */
    Aluno atualizarAluno(Aluno aluno) throws EntidadeNotFoundException;

    /**
     * Remove um Aluno do sistema.
     *
     * @param aluno Aluno a ser removido.
     */
    void removerAluno(Aluno aluno) throws EntidadeNotFoundException;

    Aluno procurarPorEmail(String email) throws EntidadeNotFoundException;
}
