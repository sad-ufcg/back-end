package com.ufcg.sad.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ufcg.sad.models.questao.Questao;

/**
 * Repositório para Questão
 * 
 * @author Lucas Silva
 */
public interface QuestaoRepository extends JpaRepository<Questao, Long> {

    Questao findById(Long id);

}
