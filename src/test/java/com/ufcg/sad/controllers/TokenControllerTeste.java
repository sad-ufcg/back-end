package com.ufcg.sad.controllers;

import com.ufcg.sad.SadApplicationTests;
import com.ufcg.sad.models.matricula.Matricula;
import com.ufcg.sad.models.token.Token;
import com.ufcg.sad.models.util.Utils;
import com.ufcg.sad.services.token.TokenService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.ResultMatcher;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static org.springframework.http.ResponseEntity.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Created by sampaio on 21/11/17.
 */
@WebMvcTest(TokenController.class)
public class TokenControllerTeste extends SadApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TokenService tokenService;



    @Before
    public void setUp() {

    }

    @After
    public void cleanUp() {
        tokenService.deletaTodosOsTokenAtivos();
    }

    @Test
    public void getTokenAtivo() throws Exception {
        String id = createTokenTest("Pedro", "Fisica IV");


        mvc.perform(get("/token").contentType(MediaType.APPLICATION_JSON).content(id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect((ResultMatcher) jsonPath("$.id", is(id)));
    }



    private String createTokenTest(String nomeAluno, String nomeDisciplina) {
        Matricula matricula = Utils.createMatriculaTest(nomeAluno, nomeDisciplina);
        Token token = new Token(matricula);
        tokenService.criaToken(token);
        return token.getId();
    }


}
