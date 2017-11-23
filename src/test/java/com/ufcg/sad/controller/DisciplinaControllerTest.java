package com.ufcg.sad.controller;

import com.ufcg.sad.SadApplicationTests;
import com.ufcg.sad.controllers.DisciplinaController;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(DisciplinaController.class)
public class DisciplinaControllerTest extends SadApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void verificaGetAll() throws Exception {
        mockMvc.perform(get("/disciplinas")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isFound());
    }

}
