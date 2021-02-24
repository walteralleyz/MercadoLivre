package br.com.zup.MercadoLivre.integration.question;

import br.com.zup.MercadoLivre.integration.util.JsonBuilder;
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
public class QuestionTest {
    private final MockMvc mvc;
    private RequestBuilder requestBuilder;
    private JsonBuilder jsonBuilder;

    @Autowired
    public QuestionTest(MockMvc mvc) {
        this.mvc = mvc;
    }

    @BeforeEach
    public void setUp() {
        requestBuilder = new RequestBuilder(mvc);
        jsonBuilder = new JsonBuilder();
    }

    @Test
    @DisplayName(value = "Cadastrar nova pergunta")
    @WithUserDetails("user@mail.com")
    public void shouldCreateNewQuestion() throws Exception {
        URI uri = new URI("/api/question");

        String content = jsonBuilder
            .property("title", "teste")
            .property("product_id", 1)
            .compact();

        String response = requestBuilder.uri(uri).content(content).status(200).post();

        System.out.println(response);
    }
}
