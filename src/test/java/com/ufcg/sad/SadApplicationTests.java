package com.ufcg.sad;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.json.Json;

/**
 * Template de testes para a aplicação
 */
@RunWith(SpringRunner.class)
public class SadApplicationTests {

    @Test
	public void contextLoads() {
	}

    protected MvcResult logar(MockMvc mockMvc) throws Exception {
        String username = "matheus.gaudencio@computacao.ufcg.edu.br";
        String password = "12345678";

        String body = Json.createObjectBuilder()
                .add("login", username)
                .add("senha", password)
                .build()
                .toString();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/auth")
                .content(body))
                .andReturn();

        return result;
    }

}
