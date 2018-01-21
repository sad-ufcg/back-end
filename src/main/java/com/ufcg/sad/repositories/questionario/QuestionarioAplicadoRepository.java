package com.ufcg.sad.repositories.questionario;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ufcg.sad.models.questionario.QuestionarioAplicado;

import java.util.List;

/**
 * Repositório para Questionário Aplicado
 * 
 * @author Arthur Vinícius
 */
public interface QuestionarioAplicadoRepository extends JpaRepository<QuestionarioAplicado, Long> {


    /**
     * Recupera uma lista de Questionários Aplicados.
     *
     * @param ids
     *          Ids dos Questionários Aplicados a serem recuperados.
     * @return
     *          Lista de Questionários Aplicados.
     */
    List<QuestionarioAplicado> findByIdIn(List<Long> ids);
}