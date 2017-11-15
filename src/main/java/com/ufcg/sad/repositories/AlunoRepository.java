package com.ufcg.sad.repositories;

import com.ufcg.sad.models.aluno.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório de Aluno
 *
 * @author Antunes Dantas
 */
public interface AlunoRepository extends JpaRepository<Aluno, Long>{
}
