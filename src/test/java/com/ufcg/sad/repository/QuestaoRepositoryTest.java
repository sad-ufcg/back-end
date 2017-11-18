package com.ufcg.sad.repository;

import com.ufcg.sad.SadApplicationTests;
import com.ufcg.sad.models.questionario.Questao;
import com.ufcg.sad.models.questionario.TipoResposta;
import com.ufcg.sad.repositories.questionario.QuestaoRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by sampaio on 16/11/17.
 * Classe para testes do Repository de Questão
 */
@DataJpaTest
public class QuestaoRepositoryTest extends SadApplicationTests {

    @Autowired
    private QuestaoRepository questaoRepository;

    private Questao questao;

    @Autowired
    private TestEntityManager entityManager;



    @Before
    public void setUp() {
        questao = new Questao("Questao de testes",
                                TipoResposta.MULTIPLA_ESCOLHA,
                                "algum comentario qualquer");
    }

    @Test
    public void retornaQuestaoPeloID () {

        entityManager.persist(questao);
        entityManager.flush();

        Questao encontrada = questaoRepository.findById(questao.getId());

        assertThat(encontrada.getId()).isEqualTo(questao.getId());

    }

}
