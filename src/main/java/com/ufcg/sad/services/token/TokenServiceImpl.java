package com.ufcg.sad.services.token;

import com.ufcg.sad.exceptions.EntidadeNotFoundException;
import com.ufcg.sad.exceptions.utils.ParametroInvalidoException;
import com.ufcg.sad.models.questionario.QuestionarioAplicado;
import com.ufcg.sad.models.token.Token;
import com.ufcg.sad.repositories.token.TokenRepository;
import com.ufcg.sad.services.questionario.QuestionarioAplicadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sampaio on 21/11/17.
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    QuestionarioAplicadoService questionarioAplicadoService;

    @Override
    public Token verificaSeTokenExiste(String tokenID) throws EntidadeNotFoundException {
        Token tokenEncontrado = tokenRepository.findById(tokenID);
        if (tokenEncontrado != null){
            return tokenEncontrado;
        }
        throw new EntidadeNotFoundException("Este Token não existe.");
    }

    @Override
    public List<Token> deletaTodosOsTokenAtivos() {
        List<Token> list = tokenRepository.findAll();
        tokenRepository.deleteAll();
        return list;
    }

    @Override
    public Token criaToken(Token token) throws EntidadeNotFoundException {
        if(token != null && token.getIdQuestionarioAplicado() != null){
            Token tokenSalvo = tokenRepository.save(token);
            QuestionarioAplicado questionarioAplicado = questionarioAplicadoService
                    .getQuestionarioAplicado(token.getIdQuestionarioAplicado());
            questionarioAplicado.addToken(token);
            questionarioAplicadoService.atualizaQuestionarioAplicado(questionarioAplicado);

            return tokenSalvo;
        } else {
        	throw new ParametroInvalidoException();
        }
    }
}
