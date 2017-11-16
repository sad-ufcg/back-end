package com.ufcg.sad.repository;

import com.ufcg.sad.SadApplicationTests;
import com.ufcg.sad.models.matricula.Matricula;
import com.ufcg.sad.models.questionario.Questao;
import com.ufcg.sad.models.token.Token;
import com.ufcg.sad.models.util.Utils;
import com.ufcg.sad.repositories.token.TokenRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by sampaio on 16/11/17.
 */
@DataJpaTest
public class TokenRepositoryTest extends SadApplicationTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TokenRepository tokenRepository;

    private Token token;

    @Before
    public void setUp() {
        Matricula matricula = Utils.createMatriculaTest("Aluno", "Disciplina");
        token = new Token(matricula);

    }

    @Test
    public void retornaQuestaoPeloID () {

        entityManager.persist(token);
        entityManager.flush();

        Token encontrada = tokenRepository.findById(token.getId());

        assertThat(encontrada.getId()).isEqualTo(token.getId());

    }




}
