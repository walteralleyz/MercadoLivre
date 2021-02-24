package br.com.zup.MercadoLivre.integration.user;

import br.com.zup.MercadoLivre.integration.util.JsonBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;
import java.net.URISyntaxException;

import br.com.zup.MercadoLivre.integration.util.RequestBuilder;

@SpringBootTest
@AutoConfigureMockMvc
public class UserTest {
    private final MockMvc mvc;
    private RequestBuilder requestBuilder;
    private URI uri;
    private JsonBuilder jsonBuilder;

    @Autowired
    public UserTest(MockMvc mvc) {
        this.mvc = mvc;

    }

    @BeforeEach
    public void setUp() throws URISyntaxException {
        requestBuilder = new RequestBuilder(mvc);
        jsonBuilder = new JsonBuilder();
        uri = new URI("/api/user");
    }

    @Test
    @DisplayName(value = "Cadastrar usuario")
    public void deveCadastrarUmUsuario() throws Exception {
        String content = jsonBuilder
            .property("login", "visitor@mail.com")
            .property("password", "123456")
            .compact();

        requestBuilder.uri(uri).content(content).status(200).post();
    }

    @Test
    @DisplayName(value = "Email repetido")
    public void deveNegarOCadastroComEmailRepetido() throws Exception {
        String content = jsonBuilder
            .property("login", "user@mail.com")
            .property("password", "123456")
            .compact();

        requestBuilder.uri(uri).content(content).status(400).post();
    }

    @Test
    @DisplayName(value = "Email incorreto")
    public void deveRetornarErroSeEmailIncorreto() throws Exception {
        String content = jsonBuilder
            .property("login", "mail.com")
            .property("password", "123456")
            .compact();

        requestBuilder.uri(uri).content(content).status(400).post();
    }

    @Test
    @DisplayName(value = "Senha fora do padrão")
    public void deveRetornarErroSenhaIncorreta() throws Exception {
        String content = jsonBuilder
            .property("login", "user@mail.com")
            .property("password", "1234")
            .compact();

        requestBuilder.uri(uri).content(content).status(400).post();
    }
}
