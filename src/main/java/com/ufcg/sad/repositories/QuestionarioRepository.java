package com.ufcg.sad.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ufcg.sad.models.Questionario;

public interface QuestionarioRepository extends JpaRepository<Questionario, Long> {

}
