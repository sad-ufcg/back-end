package com.ufcg.sad.controllers;

import com.ufcg.sad.SadApplicationTests;
import com.ufcg.sad.controllers.ControllerParaTestes;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Exemplo de um teste para um Controller genérico.
 * @author Arthur Sampaio
 */
@WebMvcTest(ControllerParaTestes.class)
public class ControllerTestes extends SadApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void verificaSeControllerFunciona() throws Exception {
        mvc.perform(get("/testes/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound());

    }
}
