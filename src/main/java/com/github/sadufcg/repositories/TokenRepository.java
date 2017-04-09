package com.github.sadufcg.repositories;

import com.github.sadufcg.pojo.Token;

public interface TokenRepository extends BaseRepository<Token, String> {

    Token findOne(String id);
    
    Token save(Token token);

}
