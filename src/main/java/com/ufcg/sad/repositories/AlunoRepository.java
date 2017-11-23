package com.ufcg.sad.repositories;

import com.ufcg.sad.models.aluno.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repositório de Aluno
 *
 * @author Antunes Dantas
 */
@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    @Query("select a from Aluno a WHERE a.email = ?1")
    Aluno findByEmail(String email);
}
