package br.com.zup.MercadoLivre.question;

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
public class QuestionTest {

    @Autowired
    private MockMvc mvc;

    private String token;

    @BeforeEach
    public void setUp() throws Exception {
        token = generateToken(mvc, "user@mail.com", "123456");
    }

    @Test
    @DisplayName(value = "Cadastrar nova pergunta")
    public void shouldCreateNewQuestion() throws Exception {
        URI uri = new URI("/api/question");
        String content = "{\"title\": \"teste\", \"product_id\": 1}";

        String response = performPost(mvc, uri, content, 200, token);

        System.out.println(response);
    }
}
