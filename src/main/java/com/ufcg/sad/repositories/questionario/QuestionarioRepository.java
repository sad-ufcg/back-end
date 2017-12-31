package com.ufcg.sad.repositories.questionario;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ufcg.sad.models.questionario.Questionario;

/**
 * Repositório para Questionário
 * 
 * @author Lucas Silva
 */
public interface QuestionarioRepository extends JpaRepository<Questionario, Long> {

}
