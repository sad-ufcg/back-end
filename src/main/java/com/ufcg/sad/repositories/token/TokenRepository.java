package com.ufcg.sad.repositories.token;

import com.ufcg.sad.models.token.Token;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório para a Entidade Token
 * Created by Arthur Sampaio on 16/11/17.
 */
public interface TokenRepository extends JpaRepository<Token, Long> {

    Token findById(String id);


}
