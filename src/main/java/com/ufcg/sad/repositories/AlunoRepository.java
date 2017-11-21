package com.ufcg.sad.repositories;

import com.ufcg.sad.models.aluno.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório de Aluno
 *
 * @author Antunes Dantas
 */
@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    Aluno findByEmail(String email);
}
