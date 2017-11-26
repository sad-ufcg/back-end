package com.ufcg.sad.repositories;

import com.ufcg.sad.models.aluno.Aluno;
import com.ufcg.sad.models.matricula.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Repositório para Matricula
 *
 * @author Antunes Dantas
 */
public interface MatriculaRepository extends JpaRepository<Matricula, Long> {

    @Query("select distinct m.aluno from Matricula m where m.disciplina.id = ?1")
    List<Aluno> getAlunosMatriculados(Long idDisciplina);
}
