package com.ufcg.sad.services.questionario;

import com.ufcg.sad.exceptions.EntidadeNotFoundException;
import com.ufcg.sad.models.disciplina.Disciplina;

/**
 * Serviços relacionados a entidade Disciplina.
 *
 * @author Antunes Dantas
 */
public interface DisciplinaService {

    /**
     * Cadastra uma Disciplina no sistema
     *
     * @param disciplina Disciplina a ser adicionada no sistema.
     *
     * @return Disciplina cadastrada.
     */
    Disciplina cadastrarDisciplina(Disciplina disciplina);

    /**
     * Recupera uma disciplina através do Id
     *
     * @param id Id da Disciplina.
     *
     * @return Disciplina.
     */
    Disciplina getDisciplina(Long id) throws EntidadeNotFoundException;

    /**
     * Atualiza uma Disciplina no sistema
     *
     * @param disciplina Disciplina atualizada.
     *
     * @return Discioplina atualizada.
     */
    Disciplina atualizarDisciplina(Disciplina disciplina);

    /**
     * Remove uma discilina do sistema.
     *
     * @param disciplina Disciplina a ser removida.
     */
    void removerDisciplina(Disciplina disciplina) throws EntidadeNotFoundException;

}
