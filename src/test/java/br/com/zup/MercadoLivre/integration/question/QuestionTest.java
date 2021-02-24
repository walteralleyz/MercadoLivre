package br.com.zup.MercadoLivre.integration.question;

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

    @Autowired
    public QuestionTest(MockMvc mvc) {
        this.mvc = mvc;
    }

    @BeforeEach
    public void setUp() {
        requestBuilder = new RequestBuilder(mvc);
    }

    @Test
    @DisplayName(value = "Cadastrar nova pergunta")
    @WithUserDetails("user@mail.com")
    public void shouldCreateNewQuestion() throws Exception {
        URI uri = new URI("/api/question");
        String content = "{\"title\": \"teste\", \"product_id\": 1}";

        String response = requestBuilder.uri(uri).content(content).status(200).post();

        System.out.println(response);
    }
}
