package com.ufcg.sad.services.professor;

import com.ufcg.sad.exceptions.EntidadeNotFoundException;
import com.ufcg.sad.models.professor.Professor;

import java.util.List;

/**
 * Interface para os serviços de Professor.
 *
 * @author Antunes Dantas
 */
public interface ProfessorService {

    /**
     * Cadastra um novo Professor.
     *
     * @param professor Professor a ser cadastrado
     *
     * @return Professor cadastrado.
     */
    Professor criarProfessor(Professor professor);

    /**
     * Recupera um Professor através do Id
     *
     * @param id Id do Professor.
     *
     * @return Professor.
     */
    Professor getProfessor(Long id) throws EntidadeNotFoundException;

    /**
     * Recupera todos os Professores cadastrados.
     *
     * @return Lista com as Professores.
     */
    List<Professor> getTodosOsProfessores();

    /**
     * Atualiza um Professor no sistema
     *
     * @param professor Professor a ser atualizado.
     *
     * @return Professor atualizado.
     */
    Professor atualizarProfessor(Professor professor) throws EntidadeNotFoundException;

    /**
     * Remove um Professor do sistema.
     *
     * @param professor Professor a ser removido.
     */
    void removerProfessor(Professor professor) throws EntidadeNotFoundException;

    /**
     * Procura um Professor pelo seu número Siape.
     *
     * @param siape Código Siape do Professor a ser procurado.
     *
     * @return Professor
     *
     * @throws EntidadeNotFoundException Lança exceção caso o professor não exista.
     */
    Professor procurarProfessorPeloSiape(String siape) throws EntidadeNotFoundException;
}
