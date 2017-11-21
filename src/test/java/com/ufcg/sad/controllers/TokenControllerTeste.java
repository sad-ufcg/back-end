package com.ufcg.sad.controllers;

import com.ufcg.sad.SadApplication;
import com.ufcg.sad.SadApplicationTests;
import com.ufcg.sad.models.matricula.Matricula;
import com.ufcg.sad.models.token.Token;
import com.ufcg.sad.models.util.Utils;
import com.ufcg.sad.repositories.AlunoRepository;
import com.ufcg.sad.repositories.DisciplinaRepository;
import com.ufcg.sad.repositories.MatriculaRepository;
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
    private MatriculaRepository matriculaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

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
    public void tokenInexistente_entaoTokenInvalido() throws Exception {

        String tokenID = "AlgumToken%20";

        mvc.perform(get("/token")
                .contentType(MediaType.APPLICATION_JSON)
                .param("tokenID", tokenID))
                .andDo(print())
                .andExpect(status().isNotFound());

    }



    private Token createTokenTest(String nomeAluno, String nomeDisciplina) {
        Matricula matricula = Utils.createMatriculaTest(nomeAluno, nomeDisciplina);
        Token token = new Token(matricula);
        alunoRepository.saveAndFlush(matricula.getAluno());
        disciplinaRepository.saveAndFlush(matricula.getDisciplina());

        matriculaRepository.saveAndFlush(matricula);
        tokenRepository.saveAndFlush(token);
        return token;
    }


}
