package com.ufcg.sad.repositories.questionario;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ufcg.sad.models.questionario.Questao;

/**
 * Repositório para Questão
 * 
 * @author Lucas Silva
 */
public interface QuestaoRepository extends JpaRepository<Questao, Long> {

}
