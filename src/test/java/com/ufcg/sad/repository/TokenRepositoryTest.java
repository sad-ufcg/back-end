package com.ufcg.sad.repository;

import com.ufcg.sad.SadApplicationTests;
import com.ufcg.sad.models.matricula.Matricula;
import com.ufcg.sad.models.token.Token;
import com.ufcg.sad.models.util.Utils;
import com.ufcg.sad.repositories.token.TokenRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Java6Assertions.assertThat;

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

    @Test
    public void retonaTokenPeloID () {
 
        Matricula matricula = Utils.createMatriculaTest("Aluno", "Disciplina");
        token = new Token(matricula);
        entityManager.persist(matricula.getAluno());
        entityManager.persist(matricula.getDisciplina());
        entityManager.persist(token);
        entityManager.persist(matricula);
        entityManager.flush();
        Token encontrada = tokenRepository.findById(token.getId());
        assertThat(encontrada.getId()).isEqualTo(token.getId());

    }

    @Test
    public void idInvalido_entãoNaoretornaToken () {

        Matricula matricula = Utils.createMatriculaTest("Aluno", "Disciplina");
        token = new Token(matricula);
        entityManager.persist(matricula.getAluno());
        entityManager.persist(matricula.getDisciplina());
        entityManager.persist(token);
        entityManager.persist(matricula);
        matricula = Utils.createMatriculaTest("OutroAlunoAluno", "OutraDisciplina");
        Token outroToken = new Token(matricula);
        entityManager.persist(matricula.getAluno());
        entityManager.persist(matricula.getDisciplina());
        entityManager.persist(outroToken);
        entityManager.persist(matricula);
        entityManager.flush();
        Token encontrada = tokenRepository.findById(token.getId());
        assertThat(encontrada.getId()).isNotEqualTo(outroToken.getId());

    }

}
