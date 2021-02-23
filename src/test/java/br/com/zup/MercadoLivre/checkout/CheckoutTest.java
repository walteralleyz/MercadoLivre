package br.com.zup.MercadoLivre.checkout;

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
public class CheckoutTest {

    @Autowired
    private MockMvc mvc;

    private String token;

    @BeforeEach
    public void setUp() throws Exception {
        URI uri = new URI("/api/security");
        String content = "{\"login\": \"user@mail.com\", \"password\": \"123456\"}";

        token = extractToken(performPost(mvc, uri, content, 200, "test"));
    }

    @Test
    @DisplayName(value = "Criar um checkout com quantidade alem")
    public void shouldNotCreateAPayment() throws Exception {
        URI uri = new URI("/api/checkout");
        String content = "{\"product_id\": 1, \"productQuantity\": 20, \"status\": 0, \"payment\": 0}";

        String response = performPost(mvc, uri, content, 400, token);

        System.out.println(response);
    }

    @Test
    @DisplayName(value = "Criar um checkout")
    public void shouldCreateAPayment() throws Exception {
        URI uri = new URI("/api/checkout");
        String content = "{\"product_id\": 1, \"productQuantity\": 9, \"status\": 0, \"payment\": 0}";

        String response = performPost(mvc, uri, content, 302, token);

        System.out.println(response);
    }

}
