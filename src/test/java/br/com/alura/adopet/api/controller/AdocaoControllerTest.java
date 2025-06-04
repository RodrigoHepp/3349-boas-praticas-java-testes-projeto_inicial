package br.com.alura.adopet.api.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import br.com.alura.adopet.api.service.AdocaoService;

@SpringBootTest
@AutoConfigureMockMvc
public class AdocaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdocaoService service;


    @Test
    void deveriaDevolverCodigo400SolicitarAdocaoController() throws Exception {
        //Arange
        String json = "{}";

        // Act
        var response = mockMvc.perform(
            post("/adocoes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        ).andReturn().getResponse();
        // Assert
        Assertions.assertEquals(400, response.getStatus());
    }

      @Test
    void deveriaDevolverCodigo200SolicitarAdocaoController() throws Exception {
        //Arange
         String json = """
            {
                "idPet": 1,
                "idTutor": 1,
                "motivo": "Motivo qualquer"
            }
            """;

        // Act
        var response = mockMvc.perform(
            post("/adocoes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        ).andReturn().getResponse();
        // Assert
        Assertions.assertEquals(200, response.getStatus());
    }
}
