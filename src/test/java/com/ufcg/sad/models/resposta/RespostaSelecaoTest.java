package com.ufcg.sad.models.resposta;

import com.ufcg.sad.models.disciplina.Disciplina;
import com.ufcg.sad.models.opcao.Opcao;
import com.ufcg.sad.models.professor.Professor;
import com.ufcg.sad.models.questao.Questao;
import com.ufcg.sad.models.questao.TipoQuestao;
import com.ufcg.sad.models.questionario.Questionario;
import com.ufcg.sad.models.questionario.QuestionarioAplicado;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Classe de teste para RespostaSelecao
 *
 * @author Arthur Costa
 */
public class RespostaSelecaoTest {

    @Test
    public void testaConstrutor() {
        Professor autor = new Professor("siape", "Pedro", new HashSet<Disciplina>(), new QuestionarioAplicado());
        Date dataCriacao = new Date();
        List<Opcao> opcoes = new ArrayList<Opcao>();

        Questao questao = new Questao(new Long(1), "A ementa da disciplina foi seguida adequadamente?", autor, dataCriacao, dataCriacao, "", opcoes, TipoQuestao.ESCOLHA_SIMPLES, new Resposta());

        Professor professor = new Professor("siape", "João", new HashSet<Disciplina>(), null);
        Questionario questionario = new Questionario(new Long(1), "Questionario", "Uma descrição", new HashSet<Questao>(), professor, new Date(), new Date(), new HashSet<QuestionarioAplicado>());

        QuestionarioAplicado questionarioAplicado = new QuestionarioAplicado(questionario, professor, new Disciplina(), new HashSet<Resposta>());

        RespostaSelecao respostaSelecao= new RespostaSelecao(dataCriacao, questao, questionarioAplicado, new HashSet<Opcao>());

        assertEquals(respostaSelecao.getOpcoesSelecionadas().size(), 0);

        criaOpcoes(respostaSelecao);

        assertEquals(respostaSelecao.getOpcoesSelecionadas().size(), 2);
    }

    private void criaOpcoes(RespostaSelecao respostaSelecao) {
        Professor autor = new Professor("siape", "Pedro", new HashSet<Disciplina>(), new QuestionarioAplicado());
        Date dataCriacao = new Date();
        List<Opcao> opcoes = new ArrayList<Opcao>();

        Questao questao = new Questao(new Long(1), "A ementa da disciplina foi seguida adequadamente?", autor, dataCriacao, dataCriacao, "", opcoes, TipoQuestao.ESCOLHA_SIMPLES, new Resposta());

        Opcao opcao1 = new Opcao(new Long(1), "opcao 1", "primeira opcao", questao);
        Opcao opcao2 = new Opcao(new Long(2), "opcao 2", "segunda opcao", questao);

        Set<Opcao> opcoesAdd = new HashSet<Opcao>();

        opcoesAdd.add(opcao1);
        opcoesAdd.add(opcao2);

        respostaSelecao.setOpcoesSelecionadas(opcoesAdd);
    }
}
