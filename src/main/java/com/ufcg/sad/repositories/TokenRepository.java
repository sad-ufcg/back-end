package com.ufcg.sad.repositories;

import com.ufcg.sad.models.token.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repositório para a Entidade Token
 * Created by Arthur Sampaio on 16/11/17.
 */
public interface TokenRepository extends JpaRepository<Token, Long> {

    Token findById(String id);
    
    @Transactional
    @Modifying
	@Query("DELETE Token t WHERE t.id = :token") 
	void delete(@Param("token") String token);

}
