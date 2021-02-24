package br.com.zup.MercadoLivre.integration.category;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;

import br.com.zup.MercadoLivre.integration.util.RequestBuilder;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoryTest {
    private final MockMvc mvc;
    private RequestBuilder requestBuilder;

    @Autowired
    public CategoryTest(MockMvc mvc) {
        this.mvc = mvc;
    }

    @BeforeEach
    public void setUp() {
        requestBuilder = new RequestBuilder(mvc);
    }

    @Test
    @DisplayName(value = "Cadastrar categorias com mãe")
    @WithUserDetails("user@mail.com")
    public void deveriaCadastrarCategorias() throws Exception {
        URI uri = new URI("/api/category");
        String content = "{\"name\": \"molho\", \"category_id\": 1}";

        String response = requestBuilder.uri(uri).content(content).status(200).post();

        System.out.println(response);
    }
}
