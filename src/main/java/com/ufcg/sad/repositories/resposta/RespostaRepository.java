package com.ufcg.sad.repositories.resposta;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufcg.sad.models.questao.Questao;

/**
 * Repositório para Resposta
 * 
 * @author Marianne Linhares
 */
public interface RespostaRepository extends JpaRepository<Questao, Long> {

    Questao findById(Long id);

}
