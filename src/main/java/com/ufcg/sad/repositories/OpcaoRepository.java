package com.ufcg.sad.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ufcg.sad.models.opcao.Opcao;

/**
 * Repositório para Opção
 * 
 * @author Marianne Linhares
 */
public interface OpcaoRepository extends JpaRepository<Opcao, Long> {

    Opcao findById(Long id);
}
