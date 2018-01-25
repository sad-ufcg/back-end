package com.ufcg.sad.services.token;

import com.ufcg.sad.models.questionario.QuestionarioAplicado;
import com.ufcg.sad.exceptions.EntidadeInvalidaException;
import com.ufcg.sad.exceptions.EntidadeNotFoundException;
import com.ufcg.sad.models.disciplina.Disciplina;
import com.ufcg.sad.models.questionario.Questionario;
import com.ufcg.sad.models.token.Token;
import java.util.List;

/**
 * Created by sampaio on 21/11/17.
 */
public interface TokenService {


    /**
     * Verifica se para se um dado token existe no sistema
     * @param tokenID a ser verificado
     * @return um token se existir, null caso contrário.
     */
    Token verificaSeTokenExiste (String tokenID) throws EntidadeNotFoundException, Exception;

    /**
     * Deleta todos os tokens ativos no sistema
     * @return todos os token ativos até o a execução deste método
     */
    List<Token> deletaTodosOsTokenAtivos();

    /**
     * Cria um novo token
     * @param token token a ser criado
     * @return token criado
     * @throws EntidadeNotFoundException, EntidadeInvalidaException 
     */
    Token criaToken (Token token) throws EntidadeNotFoundException, EntidadeInvalidaException;

	Questionario buscarQuestionario(String tokenID) throws EntidadeNotFoundException;

	QuestionarioAplicado buscarQuestionarioAplicado(String tokenId) throws EntidadeNotFoundException;

	void deletaToken(String token) throws EntidadeNotFoundException, EntidadeInvalidaException;

	Disciplina buscarDisciplina(String tokenId) throws EntidadeNotFoundException;
}
