package com.ufcg.sad.repositories;

import com.ufcg.sad.models.disciplina.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório para Professor.
 *
 * @author Antunes Dantas
 */
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {

}
