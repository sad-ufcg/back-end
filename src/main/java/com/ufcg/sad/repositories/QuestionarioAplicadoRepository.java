package com.ufcg.sad.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ufcg.sad.models.questionario.QuestionarioAplicado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repositório para Questionário Aplicado
 * 
 * @author Arthur Vinícius
 */
public interface QuestionarioAplicadoRepository extends JpaRepository<QuestionarioAplicado, Long> {

    /**
     * Recupera uma lista de Questionários Aplicados.
     *
     * @param ids
     *          Ids dos Questionários Aplicados a serem recuperados.
     * @return
     *          Lista de Questionários Aplicados.
     */
    List<QuestionarioAplicado> findByIdIn(List<Long> ids);
    
    @Query("SELECT new QuestionarioAplicado(q,"
    		+ " (SELECT d.nome FROM Disciplina d WHERE d.id = q.idDisciplina),"
    		+ " (SELECT d.turma FROM Disciplina d WHERE d.id = q.idDisciplina),"
    		+ " (SELECT d.semestre FROM Disciplina d WHERE d.id = q.idDisciplina))"
    		+ " FROM QuestionarioAplicado q WHERE q.idQuestionario = :idQuestionario")
    List<QuestionarioAplicado> findByIdQuestionario(@Param("idQuestionario")Long idQuestionario);
    
    
    @Query("SELECT new QuestionarioAplicado(q,"
    		+ " (SELECT d.nome FROM Disciplina d WHERE d.id = q.idDisciplina AND d.semestre = :semestre),"
    		+ " (SELECT d.turma FROM Disciplina d WHERE d.id = q.idDisciplina AND d.semestre = :semestre),"
    		+ " (SELECT d.semestre FROM Disciplina d WHERE d.id = q.idDisciplina AND d.semestre = :semestre))"
    		+ " FROM QuestionarioAplicado q WHERE q.idQuestionario = :idQuestionario")
    List<QuestionarioAplicado> findByIdQuestionario(@Param("idQuestionario")Long idQuestionario,
    												@Param("semestre") String semestre);
    
    @Query("SELECT new QuestionarioAplicado(q,"
    		+ " (SELECT d.nome FROM Disciplina d WHERE d.id = q.idDisciplina),"
    		+ " (SELECT d.turma FROM Disciplina d WHERE d.id = q.idDisciplina),"
    		+ " (SELECT d.semestre FROM Disciplina d WHERE d.id = q.idDisciplina))"
    		+ " FROM QuestionarioAplicado q WHERE q.idQuestionario = :idQuestionario and q.idDisciplina = :idDisciplina")
    List<QuestionarioAplicado> findByIdQuestionarioAndIdDisciplina(@Param("idQuestionario") Long idQuestionario,
    		                                                       @Param("idDisciplina") Long idDisciplina);
    
    @Query("SELECT new QuestionarioAplicado(q,"
    		+ " (SELECT d.nome FROM Disciplina d WHERE d.id = q.idDisciplina AND d.semestre = :semestre),"
    		+ " (SELECT d.turma FROM Disciplina d WHERE d.id = q.idDisciplina AND d.semestre = :semestre),"
    		+ " (SELECT d.semestre FROM Disciplina d WHERE d.id = q.idDisciplina AND d.semestre = :semestre))"
    		+ " FROM QuestionarioAplicado q WHERE q.idQuestionario = :idQuestionario and q.idDisciplina = :idDisciplina")
    List<QuestionarioAplicado> findByIdQuestionarioAndIdDisciplina(@Param("idQuestionario") Long idQuestionario,
    		                                                       @Param("idDisciplina") Long idDisciplina,
    		                                                       @Param("semestre") String semestre);
    
    @Override
    @Query("SELECT new QuestionarioAplicado(q.id, q.idQuestionario, q.idDisciplina,"
    	   + "q.idProfessor,"
    	   + "(SELECT d.nome FROM Disciplina d WHERE d.id = q.idDisciplina))"
    	   + "FROM QuestionarioAplicado q")
    List<QuestionarioAplicado> findAll();
    
    @Query("SELECT q.idDisciplina FROM QuestionarioAplicado q"
    		+ " WHERE q.idQuestionario = :idQuestionario")
	List<Long> findDisciplinaQuestionario(@Param("idQuestionario") Long idQuestionario);

    // FIXME: poderia ser feito um inner join de questionarioAplicado e Disciplina
    @Query("SELECT q.idDisciplina FROM QuestionarioAplicado q, Disciplina d"
    		+ " WHERE q.idQuestionario = :idQuestionario AND d.id = q.idDisciplina AND"
    		+ " d.semestre = :semestre")
	List<Long> findDisciplinaQuestionario(@Param("idQuestionario") Long idQuestionario,
										  @Param("semestre") String semestre);
}