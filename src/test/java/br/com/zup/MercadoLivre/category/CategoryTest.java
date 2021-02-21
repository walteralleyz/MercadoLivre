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
    @DisplayName(value = "Cadastrar categorias")
    public void deveriaCadastrarCategorias() throws Exception {
        URI uri = new URI("/api/category");
        String molho = "{\"name\": \"molho\"}";
        String abacaxi = "{\"name\": \"abacaxi\"}";
        String repolho = "{\"name\": \"repolho\"}";

        performPost(uri, molho, 200);
        performPost(uri, abacaxi, 200);
        performPost(uri, repolho, 200);
    }

    @Test
    @DisplayName(value = "Cadastrar categoria com categoria")
    public void deveriaCadastrarCategoriaComCategorias() throws Exception {
        URI uri = new URI("/api/category");
        String content = "{\"name\": \"comida\", \"children_id\": [1, 2, 3]}";

        performPost(uri, content, 200);
    }

    @Test
    @DisplayName(value = "Não deve cadastrar com id incorreto")
    public void naoDeveriaCadastrarComFilhosInexistentes() throws Exception {
        URI uri = new URI("/api/category");
        String content = "{\"name\": \"comida\", \"children_id\": [1, 2, 4]}";

        performPost(uri, content, 400);
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
