package br.com.zup.MercadoLivre.rating;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;

import static br.com.zup.MercadoLivre.util.Request.performPost;
import static br.com.zup.MercadoLivre.util.Token.extractToken;

@SpringBootTest
@AutoConfigureMockMvc
public class RatingTest {

    @Autowired
    private MockMvc mvc;

    private String token;

    @BeforeEach
    public void setUp() throws Exception {
        URI uri = new URI("/api/security");
        String content = "{\"login\": \"user@mail.com\", \"password\": \"123456\"}";

        token = extractToken(performPost(mvc, uri, content, 200, "test"));

        URI product = new URI("/api/product");
        String productContent = "{" +
            "\"name\": \"tomate\"," +
            "\"price\": 2.99," +
            "\"quantity\": 4," +
            "\"details\": [" +
            "{\"title\": \"cor\", \"text\": \"vermelho\"}," +
            "{\"title\": \"tamanho\", \"text\": \"medio\"}," +
            "{\"title\": \"qualidade\", \"text\": \"bom\"}" +
            "]," +
            "\"description\": \"fruto para ser usada em saladas\"," +
            "\"category_id\": 1" +
            "}";

        performPost(mvc, product, productContent, 200, token);
    }

    @Test
    @DisplayName(value = "Cadastrar opinião")
    public void shouldCreateRating() throws Exception {
        URI uri = new URI("/api/rating");
        String content = "{\"level\": 3, \"title\": \"ok\", \"description\": \"not that\", \"product_id\": 1}";

        String response = performPost(mvc, uri, content, 200, token);

        System.out.println(response);
    }

    @Test
    @DisplayName(value = "Cadastrar opinião sem login")
    public void shouldErrorTryingToCreateRatingWithoutLogin() throws Exception {
        URI uri = new URI("/api/rating");
        String content = "{\"level\": 3, \"title\": \"ok\", \"description\": \"not that\", \"product_id\": 1}";

        String response = performPost(mvc, uri, content, 403, "test");

        System.out.println(response);
    }
}
