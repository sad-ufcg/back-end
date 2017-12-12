package com.ufcg.sad.controllers;

import com.ufcg.sad.SadApplication;
import com.ufcg.sad.SadApplicationTests;
import com.ufcg.sad.models.disciplina.Disciplina;
import com.ufcg.sad.models.professor.Professor;
import com.ufcg.sad.models.questao.Questao;
import com.ufcg.sad.models.questionario.Questionario;
import com.ufcg.sad.models.questionario.QuestionarioAplicado;
import com.ufcg.sad.models.resposta.Resposta;
import com.ufcg.sad.models.token.Token;
import com.ufcg.sad.repositories.DisciplinaRepository;
import com.ufcg.sad.repositories.ProfessorRepository;
import com.ufcg.sad.repositories.questionario.QuestionarioAplicadoRepository;
import com.ufcg.sad.repositories.questionario.QuestionarioRepository;
import com.ufcg.sad.repositories.token.TokenRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.HashSet;


/**
 * Created by sampaio on 21/11/17.
 */
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = SadApplication.class)
@TestPropertySource(
        locations = "classpath:application.properties")
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class TokenControllerTeste extends SadApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private QuestionarioAplicadoRepository questionarioAplicadoRepository;

    @Autowired
    private QuestionarioRepository questionarioRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;


    @Before
    public void setUp() {

    }

    @After
    public void cleanUp() {

        tokenRepository.deleteAll();
    }

    @Test
    public void getTokenAtivoPeloID() throws Exception {

        Token token = createTokenTest("Pedro", "Fisica IV");
        Assert.assertEquals(tokenRepository.findAll().size(), 1);
        mvc.perform(get("/token")
                .contentType(MediaType.APPLICATION_JSON)
                .param("tokenID", token.getId()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void naoRetornaTokenSeIDInvalido() throws Exception {

        String tokenID = "AlgumToken%20";
        mvc.perform(get("/token")
                .contentType(MediaType.APPLICATION_JSON)
                .param("tokenID", tokenID))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    private Token createTokenTest(String nomeAluno, String nomeDisciplina) {
        
    	Professor professor = new Professor("siape", "João", new HashSet<Disciplina>(), null);
    	Questionario questionario = new Questionario(new Long(1), "Questionario", "", new HashSet<Questao>(), professor, new Date(), new Date(), new HashSet<QuestionarioAplicado>());
    	Disciplina disciplina = new Disciplina();
    	disciplina.setId(new Long(1));
    	disciplina.setTurma(1);
    	disciplina.setSemestre("1");
    	disciplina.setNome(nomeDisciplina);
    	
    	QuestionarioAplicado questionarioAplicado = new QuestionarioAplicado(new Long(1), questionario, professor, disciplina, new HashSet<Resposta>());

    	Token token = new Token(questionarioAplicado);
        professorRepository.saveAndFlush(questionarioAplicado.getProfessor());
        questionarioRepository.saveAndFlush(questionarioAplicado.getQuestionario());
        disciplinaRepository.saveAndFlush(questionarioAplicado.getDisciplina());
        
        // TODO: verificar como fazer saveAndFlush para caso de coleções.
        //respostaRepository.saveAndFlush(questionarioAplicado.getRespostas());
        questionarioAplicadoRepository.saveAndFlush(questionarioAplicado);
        tokenRepository.saveAndFlush(token);
        
        return token;
    }


}
