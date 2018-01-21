package com.ufcg.sad.repository;

import com.ufcg.sad.SadApplicationTests;
import com.ufcg.sad.models.disciplina.Disciplina;
import com.ufcg.sad.models.opcao.Opcao;
import com.ufcg.sad.models.professor.Professor;
import com.ufcg.sad.models.questao.Questao;
import com.ufcg.sad.models.questao.TipoQuestao;
import com.ufcg.sad.models.util.Utils;
import com.ufcg.sad.repositories.QuestaoRepository;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Java6Assertions.assertThat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * Classe para testes do Repository de Questão
 * @author Arthur Sampaio
 */
@DataJpaTest
public class QuestaoRepositoryTest extends SadApplicationTests {

    @Autowired
    private QuestaoRepository questaoRepository;

    private Questao questao;
    private Professor autor;

    @Autowired
    private TestEntityManager entityManager;

    @Before
    public void setUp() {
    	
    	autor = new Professor("siape", "Pedro", new HashSet<Disciplina>());
		Date dataCriacao = new Date();
		List<Opcao> opcoes = new ArrayList<Opcao>();

		questao = Utils.createQuestaoTest("A ementa da disciplina foi seguida adequadamente?", autor, dataCriacao, "", opcoes, TipoQuestao.ESCOLHA_SIMPLES);
    }

    @Test
    public void retornaQuestaoPeloID () {

    	entityManager.persist(autor);
    	entityManager.flush();
		
        entityManager.persist(questao);
        entityManager.flush();

        Questao encontrada = questaoRepository.findById(questao.getId());

        assertThat(encontrada.getId()).isEqualTo(questao.getId());

    }

}
