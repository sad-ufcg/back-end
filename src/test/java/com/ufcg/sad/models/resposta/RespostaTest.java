package com.ufcg.sad.models.resposta;

import com.ufcg.sad.models.disciplina.Disciplina;
import com.ufcg.sad.models.opcao.Opcao;
import com.ufcg.sad.models.professor.Professor;
import com.ufcg.sad.models.questao.Questao;
import com.ufcg.sad.models.questao.TipoQuestao;
import com.ufcg.sad.models.questionario.Questionario;
import com.ufcg.sad.models.questionario.QuestionarioAplicado;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RespostaTest {

    @Test
    public void testaConstrutor() {

        Professor autor = new Professor("siape", "Pedro", new HashSet<Disciplina>(), new QuestionarioAplicado());
        Date dataCriacao = new Date();
        List<Opcao> opcoes = new ArrayList<Opcao>();

        Questao questao = new Questao(new Long(1), "A ementa da disciplina foi seguida adequadamente?", autor, dataCriacao, dataCriacao, "", opcoes, TipoQuestao.ESCOLHA_SIMPLES, new Resposta());

        Professor professor = new Professor("siape", "João", new HashSet<Disciplina>(), null);
        Questionario questionario = new Questionario(new Long(1), "Questionario", new HashSet<Questao>(), professor, new Date(), new Date(), new HashSet<QuestionarioAplicado>());

        QuestionarioAplicado questionarioAplicado = new QuestionarioAplicado(questionario, professor, new Disciplina(), new HashSet<Resposta>());


        Resposta resposta = new Resposta(dataCriacao, questao, questionarioAplicado);

        assertEquals(resposta.getDataResposta(), dataCriacao);
        assertEquals(resposta.getQuestao(), questao);
        assertEquals(resposta.getQuestionarioAplicado(), questionarioAplicado);
    }
}
