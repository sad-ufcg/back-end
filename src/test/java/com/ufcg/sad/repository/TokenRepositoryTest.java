package com.ufcg.sad.repository;

import com.ufcg.sad.SadApplicationTests;
import com.ufcg.sad.models.aluno.Aluno;
import com.ufcg.sad.models.matricula.Matricula;
import com.ufcg.sad.models.questionario.QuestionarioAplicado;
import com.ufcg.sad.models.resposta.Resposta;
import com.ufcg.sad.models.token.Token;
import com.ufcg.sad.repositories.TokenRepository;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Java6Assertions.assertThat;

import java.util.HashSet;

/**
 * Testes para o repositório que lida com o Token.
 * @author Arthur Sampaio.
 */
@DataJpaTest
public class TokenRepositoryTest extends SadApplicationTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TokenRepository tokenRepository;

    private Token token;
    private QuestionarioAplicado questionarioAplicado;
    private Aluno aluno;
    
    @Before
    public void setUp() {
    	questionarioAplicado = new QuestionarioAplicado(null, new Long(1), new Long(1), new Long(1), new HashSet<Resposta>(), new HashSet<Token>());
    	aluno = new Aluno(null, "Joao", "joao@ccc.ufcg.edu.br", new HashSet<Matricula>());
    	aluno.setSobrenome("Silva");
    }

    @Test
    public void retonaTokenPeloID () {

    	token = new Token(new Long(1), new Long(1));
        // TODO: persistir conjunto no futuro.
        //entityManager.persist(questionarioAplicado.getRespostas());
        entityManager.persist(questionarioAplicado);
        entityManager.persist(aluno);
        entityManager.persist(token);
        entityManager.flush();
        Token encontrada = tokenRepository.findById(token.getId());
        assertThat(encontrada.getId()).isEqualTo(token.getId());

    }

    @Test
    public void IDInvalidoNaoRetoraToken () {

        token = new Token(new Long(1), new Long(1));
        // TODO: persistir conjunto no futuro.
        //entityManager.persist(questionarioAplicado.getRespostas());
        entityManager.persist(questionarioAplicado);
        entityManager.persist(aluno);
        
        entityManager.persist(token);

        Token outroToken = new Token(new Long(1), new Long(1));
        entityManager.persist(outroToken);
        entityManager.flush();
        Token encontrada = tokenRepository.findById(token.getId());
        assertThat(encontrada.getId()).isNotEqualTo(outroToken.getId());

    }

}
