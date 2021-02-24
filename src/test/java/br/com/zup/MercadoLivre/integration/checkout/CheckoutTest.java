package br.com.zup.MercadoLivre.integration.checkout;

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
public class CheckoutTest {
    private final MockMvc mvc;
    private RequestBuilder requestBuilder;

    @Autowired
    public CheckoutTest(MockMvc mvc) {
        this.mvc = mvc;
    }

    @BeforeEach
    public void setUp() {
        requestBuilder = new RequestBuilder(mvc);
    }

    @Test
    @DisplayName(value = "Criar um checkout com quantidade alem")
    @WithUserDetails("user@mail.com")
    public void shouldNotCreateAPayment() throws Exception {
        URI uri = new URI("/api/checkout");
        String content = "{\"product_id\": 1, \"productQuantity\": 20, \"status\": 0, \"payment\": 0}";

        String response = requestBuilder.uri(uri).content(content).status(400).post();

        System.out.println(response);
    }

    @Test
    @DisplayName(value = "Criar um checkout")
    @WithUserDetails("user@mail.com")
    public void shouldCreateAPayment() throws Exception {
        URI uri = new URI("/api/checkout");
        String content = "{\"product_id\": 1, \"productQuantity\": 9, \"status\": 0, \"payment\": 0}";

        String response = requestBuilder.uri(uri).content(content).status(302).post();

        System.out.println(response);
    }

}
