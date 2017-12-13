package com.ufcg.sad.models.questionario;

import com.ufcg.sad.SadApplicationTests;
import com.ufcg.sad.models.disciplina.Disciplina;
import com.ufcg.sad.models.opcao.Opcao;
import com.ufcg.sad.models.professor.Professor;
import com.ufcg.sad.models.questao.Questao;
import com.ufcg.sad.models.questao.TipoQuestao;
import com.ufcg.sad.models.resposta.Resposta;
import com.ufcg.sad.models.resposta.RespostaAberta;
import org.junit.Test;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Classe para testar QuestionarioAplicado.
 *
 * @author Arthur Costa
 */
public class QuestionarioAplicadoTest extends SadApplicationTests {

    @Test
    public void testaConstrucao() {

//        Professor professor = new Professor("siape", "João", new HashSet<Disciplina>(), null);
//        Questionario questionario = new Questionario(new Long(1), "Questionario", "", new HashSet<Questao>(), professor, new Date(), new Date(), new HashSet<QuestionarioAplicado>());
//
//        QuestionarioAplicado questionarioAplicado = new QuestionarioAplicado(new Long(1), questionario, professor, new Disciplina(), new HashSet<Resposta>());
//
//        assertEquals(questionarioAplicado.getProfessor(), professor);
//        assertEquals(questionarioAplicado.getQuestionario(), questionario);
//        assertEquals(questionarioAplicado.getRespostas().size(), 0);
//
//        adicionaRespostas(questionarioAplicado);
//
//        assertEquals(questionarioAplicado.getRespostas().size(), 2);
    }

    private void adicionaRespostas(QuestionarioAplicado questionarioAplicado) {

        Professor autor = new Professor("siape", "Pedro", new HashSet<Disciplina>(), new QuestionarioAplicado());
        Date dataCriacao = new Date();
        List<Opcao> opcoes = new ArrayList<Opcao>();

        Questao questao = new Questao(new Long(1), "A ementa da disciplina foi seguida adequadamente?", autor, dataCriacao, dataCriacao, "", opcoes, TipoQuestao.ESCOLHA_SIMPLES, new Resposta());

        Resposta resposta1 = new RespostaAberta(dataCriacao, questao, questionarioAplicado, "aqui vai um comentario");
        Resposta resposta2 = new RespostaAberta(dataCriacao, questao, questionarioAplicado, "aqui vai um outro comentario");

        Set<Resposta> respostas = new HashSet<Resposta>();

        respostas.add(resposta1);
        respostas.add(resposta2);

        questionarioAplicado.setRespostas(respostas);
    }
}
