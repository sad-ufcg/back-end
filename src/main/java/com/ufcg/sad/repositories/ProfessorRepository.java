package com.ufcg.sad.repositories;

import com.ufcg.sad.models.professor.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório para Professor.
 *
 * @author Antunes Dantas
 */
@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    Professor findBySiape(String siape);

}
