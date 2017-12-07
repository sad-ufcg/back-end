package com.ufcg.sad.repository;

import com.ufcg.sad.SadApplicationTests;
import com.ufcg.sad.models.disciplina.Disciplina;
import com.ufcg.sad.models.professor.Professor;
import com.ufcg.sad.models.questao.Questao;
import com.ufcg.sad.models.questionario.Questionario;
import com.ufcg.sad.models.questionario.QuestionarioAplicado;
import com.ufcg.sad.models.resposta.Resposta;
import com.ufcg.sad.models.token.Token;
import com.ufcg.sad.repositories.token.TokenRepository;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Java6Assertions.assertThat;

import java.util.Date;
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
    
    @Before
    public void setUp() {
    	Professor professor = new Professor("siape", "João", new HashSet<Disciplina>(), null);
    	Questionario questionario = new Questionario(new Long(1), "Questionario", "", new HashSet<Questao>(), professor, new Date(), new Date(), new HashSet<QuestionarioAplicado>());
    	Disciplina disciplina = new Disciplina();
    	disciplina.setTurma(1);
    	disciplina.setSemestre("1");
    	disciplina.setNome("Disciplina 1");
    	questionarioAplicado = new QuestionarioAplicado(questionario, professor, disciplina, new HashSet<Resposta>());
    }

    @Test
    public void retonaTokenPeloID () {
 
    	token = new Token(questionarioAplicado);
        entityManager.persist(questionarioAplicado.getProfessor());
        entityManager.persist(questionarioAplicado.getQuestionario());
        entityManager.persist(questionarioAplicado.getDisciplina());
        entityManager.persist(questionarioAplicado.getRespostas());
        entityManager.persist(token);
        entityManager.persist(questionarioAplicado);
        entityManager.flush();
        Token encontrada = tokenRepository.findById(token.getId());
        assertThat(encontrada.getId()).isEqualTo(token.getId());

    }

    @Test
    public void IDInvalidoNaoRetoraToken () {

        token = new Token(questionarioAplicado);
        entityManager.persist(questionarioAplicado.getProfessor());
        entityManager.persist(questionarioAplicado.getDisciplina());
        entityManager.persist(questionarioAplicado.getQuestionario());
        entityManager.persist(questionarioAplicado.getRespostas());
        entityManager.persist(token);
        entityManager.persist(questionarioAplicado);
        
        // TODO: melhor criar outro questionarioAplicado no futuro.
        Token outroToken = new Token(questionarioAplicado);
        entityManager.persist(outroToken);
        entityManager.flush();
        Token encontrada = tokenRepository.findById(token.getId());
        assertThat(encontrada.getId()).isNotEqualTo(outroToken.getId());

    }

}
