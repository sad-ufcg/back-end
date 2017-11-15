package com.ufcg.sad.repositories;

import com.ufcg.sad.models.matricula.IdMatricula;
import com.ufcg.sad.models.matricula.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório para Matricula
 *
 * @author Antunes Dantas
 */
public interface MatriculaRepository extends JpaRepository<Matricula, IdMatricula> {
}
