package com.ufcg.sad.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ufcg.sad.models.Questao;

public interface QuestaoRepository extends JpaRepository<Questao, Long> {

}
