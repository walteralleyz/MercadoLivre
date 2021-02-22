package br.com.zup.MercadoLivre.category;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoryTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName(value = "Cadastrar categorias com mãe")
    public void deveriaCadastrarCategorias() throws Exception {
        URI uri = new URI("/api/category");
        String molho = "{\"name\": \"molho\", \"mother_id\": 1}";

        performPost(uri, molho, 200);
    }

    public void performPost(URI uri, String content, int status) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
            .post(uri)
            .content(content)
            .header("Accept-language", "pt")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().is(status));
    }
}
