package com.ufcg.sad.repositories.resposta;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ufcg.sad.models.resposta.Resposta;

/**
 * Repositório para Resposta
 * 
 * @author Arthur Vinícius
 */
public interface RespostaRepository extends JpaRepository<Resposta, Long> {

	@Query("SELECT r FROM Resposta r WHERE type = :tipoResposta") 
    List<Resposta> findAllOfType(@Param("tipoResposta") String tipoResposta);

	@Query("SELECT r FROM Resposta r WHERE r.idQuestao = :idQuestao") 
    List<Resposta> findAllOfQuestaoId(@Param("idQuestao") Long idQuestao);

	@Query("SELECT r FROM Resposta r WHERE r.idQuestao = :idQuestao and type = :tipoResposta") 
    List<Resposta> findAllOfTypeAndQuestaoId(@Param("tipoResposta") String tipoResposta,
    		                          @Param("idQuestao") Long idQuestao);
}