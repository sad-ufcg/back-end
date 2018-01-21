package com.ufcg.sad.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ufcg.sad.models.questionario.QuestionarioAplicado;

/**
 * Repositório para Questionário Aplicado
 * 
 * @author Arthur Vinícius
 */
public interface QuestionarioAplicadoRepository extends JpaRepository<QuestionarioAplicado, Long> {

}