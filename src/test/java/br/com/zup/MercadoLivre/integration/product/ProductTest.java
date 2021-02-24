package br.com.zup.MercadoLivre.integration.product;

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
import java.util.ArrayList;
import java.util.List;

import br.com.zup.MercadoLivre.integration.util.RequestBuilder;


@SpringBootTest
@AutoConfigureMockMvc
public class ProductTest {
    private final MockMvc mvc;
    private RequestBuilder requestBuilder;
    private JsonBuilder jsonBuilder;

    @Autowired
    public ProductTest(MockMvc mvc) {
        this.mvc = mvc;
    }

    @BeforeEach
    public void setUp() {
        requestBuilder = new RequestBuilder(mvc);
        jsonBuilder = new JsonBuilder();
    }

    /*
        Os testes estão com nomes em ordem alfabetica para que o JUnit obedeça a ordem
     */
    @Test
    @DisplayName(value = "Cadastrar produto")
    @WithUserDetails("user@mail.com")
    public void myTestA() throws Exception {
        URI imageURI = new URI("/api/product");
        String[] details = new String[]{"{\"title\": \"cor\",\"text\": \"vermelho\"},",
            "{\"title\": \"tamanho\", \"text\": \"medio\"},",
            "{\"title\": \"estado\", \"text\": \"bom\"}"};

        String imageContent = jsonBuilder
            .property("name", "beterraba")
            .property("price", "2.99")
            .property("quantity", 1)
            .property("details", details)
            .property("description", "legume")
            .property("category_id", 1)
            .compact();

        String response = requestBuilder.uri(imageURI).content(imageContent).status(200).post();

        System.out.println(response);
    }


    @Test
    @DisplayName(value = "Atualizar produto com imagens")
    @WithUserDetails("user@mail.com")
    public void myTestB() throws Exception {
        URI uri = new URI("/api/product/1/images");
        String content = "{\"images\": [{\"link\": \"teste.com\", \"product_id\": 1}, {\"link\": \"teste2.com\", \"product_id\": 1}]}";
        String response = requestBuilder.uri(uri).content(content).status(200).put();

        System.out.println(response);
    }

    @Test
    @DisplayName(value = "Recuperar um produto")
    @WithUserDetails("user@mail.com")
    public void myTestC() throws Exception {
        URI uri = new URI("/api/product/1");
        String response = requestBuilder.uri(uri).status(200).content("test").get();

        System.out.println(response);
    }

    @Test
    @DisplayName(value = "Tentar atualizar um produto com imagens com outro usuario")
    @WithUserDetails("guest@mail.com")
    public void myTestD() throws Exception {
        URI uri = new URI("/api/product/1/images");

        String content = "{\"images\": [{\"link\": \"testecomoutrouser.com\"}]}";
        String response = requestBuilder.uri(uri).content(content).status(403).put();

        System.out.println(response);
    }
}
