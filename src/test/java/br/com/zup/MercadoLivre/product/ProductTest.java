package br.com.zup.MercadoLivre.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;

import static br.com.zup.MercadoLivre.util.Auth.generateToken;
import static br.com.zup.MercadoLivre.util.Request.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductTest {

    @Autowired
    private MockMvc mvc;

    private String token;

    @BeforeEach
    public void setUp() throws Exception {
        token = generateToken(mvc, "user@mail.com", "123456");
    }

    /*
        Os testes estão com nomes em ordem alfabetica para que o JUnit obedeça a ordem
     */

    @Test
    @DisplayName(value = "Atualizar produto com imagens")
    public void myTestB() throws Exception {
        URI imageURI = new URI("/api/product/1/images");
        String imageContent = "{\"images\": [{\"link\": \"teste.com\", \"product_id\": 1}, {\"link\": \"teste2.com\", \"product_id\": 1}]}";
        String response = performPut(mvc, imageURI, imageContent, 200, token);

        System.out.println(response);
    }

    @Test
    @DisplayName(value = "Recuperar um produto")
    public void myTestC() throws Exception {
        URI uri = new URI("/api/product/1");
        String response = performGet(mvc, uri, 200, token);

        System.out.println(response);
    }

    @Test
    @DisplayName(value = "Tentar atualizar um produto com imagens com outro usuario")
    public void myTestD() throws Exception {
        URI imageURI = new URI("/api/product/1/images");

        token = generateToken(mvc, "guest@mail.com", "123456");

        String imageContent = "{\"images\": [{\"link\": \"testecomoutrouser.com\"}]}";
        String response = performPut(mvc, imageURI, imageContent, 403, token);

        System.out.println(response);
    }
}
