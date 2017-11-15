package com.ufcg.sad.repositories;

import com.ufcg.sad.models.professor.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório para Professor.
 *
 * @author Antunes Dantas
 */
public interface ProfessorRepository extends JpaRepository<Professor, String> {
}
