package br.com.zup.MercadoLivre.category;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;

import static br.com.zup.MercadoLivre.util.Auth.generateToken;
import static br.com.zup.MercadoLivre.util.Request.performPost;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoryTest {

    @Autowired
    private MockMvc mvc;

    private String token;

    @BeforeEach
    public void setUp() throws Exception {
        token = generateToken(mvc, "user@mail.com", "123456");
    }

    @Test
    @DisplayName(value = "Cadastrar categorias com mãe")
    public void deveriaCadastrarCategorias() throws Exception {
        URI uri = new URI("/api/category");
        String molho = "{\"name\": \"molho\", \"category_id\": 1}";

        String response = performPost(mvc, uri, molho, 200, token);

        System.out.println(response);
    }
}
