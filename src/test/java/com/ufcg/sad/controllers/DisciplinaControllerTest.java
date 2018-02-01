package com.ufcg.sad.controllers;

import com.ufcg.sad.SadApplication;
import com.ufcg.sad.SadApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = SadApplication.class)
@TestPropertySource(
        locations = "classpath:application.properties")
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class DisciplinaControllerTest extends SadApplicationTests {

    @Test
    public void verificaGetAll() throws Exception {
        MvcResult logar = logar();

        mockMvc.perform(get("/disciplinas")
                .header("Authentication", logar.getRequest().getHeader("Authentication"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

}
