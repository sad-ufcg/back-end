package com.ufcg.sad.services.disciplina;

import com.ufcg.sad.exceptions.EntidadeNotFoundException;
import com.ufcg.sad.models.disciplina.Disciplina;

import java.util.List;

/**
 * Serviços relacionados a entidade disciplina.
 *
 * @author Antunes Dantas
 */
public interface DisciplinaService {

    /**
     * Cadastra uma disciplina no sistema
     *
     * @param disciplina disciplina a ser adicionada no sistema.
     *
     * @return disciplina cadastrada.
     */
    Disciplina cadastrarDisciplina(Disciplina disciplina);

    /**
     * Recupera uma disciplina através do Id
     *
     * @param id Id da disciplina.
     *
     * @return disciplina.
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
     */
    Disciplina atualizarDisciplina(Disciplina disciplina);

    /**
     * Remove uma discilina do sistema.
     *
     * @param disciplina disciplina a ser removida.
     */
    void removerDisciplina(Disciplina disciplina) throws EntidadeNotFoundException;

}
