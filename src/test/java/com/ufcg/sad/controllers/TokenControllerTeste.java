package com.ufcg.sad.controllers;

import com.ufcg.sad.SadApplication;
import com.ufcg.sad.SadApplicationTests;
import com.ufcg.sad.models.questionario.QuestionarioAplicado;
import com.ufcg.sad.models.resposta.Resposta;
import com.ufcg.sad.models.token.Token;
import com.ufcg.sad.repositories.QuestionarioAplicadoRepository;
import com.ufcg.sad.repositories.TokenRepository;

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
        mvc.perform(get("/token/questionarioAplicado")
                .contentType(MediaType.APPLICATION_JSON)
                .param("tokenID", token.getId()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void naoRetornaTokenSeIDInvalido() throws Exception {

        String tokenID = "AlgumToken%20";
        mvc.perform(get("/token/questionarioAplicado")
                .contentType(MediaType.APPLICATION_JSON)
                .param("tokenID", tokenID))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    private Token createTokenTest(String nomeAluno, String nomeDisciplina) {

    	QuestionarioAplicado questionarioAplicado = new QuestionarioAplicado(null, new Long(1), new Long(1), new Long(1), new HashSet<Resposta>(), new HashSet<Token>());

    	Token token = new Token(new Long(1), new Long(1));

        questionarioAplicadoRepository.saveAndFlush(questionarioAplicado);
        tokenRepository.saveAndFlush(token);

        return token;
    }


}
