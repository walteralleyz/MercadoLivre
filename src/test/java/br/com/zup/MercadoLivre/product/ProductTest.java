package br.com.zup.MercadoLivre.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;

import static br.com.zup.MercadoLivre.util.Request.*;
import static br.com.zup.MercadoLivre.util.Token.extractToken;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductTest {

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
    @DisplayName(value = "Cadastrar produto com detalhes")
    public void myTestA() throws Exception {
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

        String response = performPost(mvc, product, productContent, 200, token);

        System.out.println(response);
    }

    @Test
    @DisplayName(value = "Atualizar produto com imagens")
    public void myTestB() throws Exception {
        URI imageURI = new URI("/api/product/1/images");
        String imageContent = "{\"images\": [{\"link\": \"teste.com\"}, {\"link\": \"teste2.com\"}]}";
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
}
