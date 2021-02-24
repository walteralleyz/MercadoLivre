package br.com.zup.MercadoLivre.integration.user;

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

    @Autowired
    public UserTest(MockMvc mvc) {
        this.mvc = mvc;
    }

    @BeforeEach
    public void setUp() throws URISyntaxException {
        requestBuilder = new RequestBuilder(mvc);
        uri = new URI("/api/user");
    }

    @Test
    @DisplayName(value = "Cadastrar usuario")
    public void deveCadastrarUmUsuario() throws Exception {
        String content = "{\"login\": \"visitor@mail.com\", \"password\": \"123456\", \"createdAt\": \"2021-02-02\"}";

        requestBuilder.uri(uri).content(content).status(200).post();
    }

    @Test
    @DisplayName(value = "Email repetido")
    public void deveNegarOCadastroComEmailRepetido() throws Exception {
        String content = "{\"login\": \"user@mail.com\", \"password\": \"123456\", \"createdAt\": \"2021-02-02\"}";

        requestBuilder.uri(uri).content(content).status(400).post();
    }

    @Test
    @DisplayName(value = "Email incorreto")
    public void deveRetornarErroSeEmailIncorreto() throws Exception {
        String content = "{\"login\": \"mail.com\", \"password\": \"123456\", \"createdAt\": \"2021-02-02\"}";

        requestBuilder.uri(uri).content(content).status(400).post();
    }

    @Test
    @DisplayName(value = "Senha fora do padrão")
    public void deveRetornarErroSenhaIncorreta() throws Exception {
        String content = "{\"login\": \"user@mail.com\", \"password\": \"1234\", \"createdAt\": \"2021-02-02\"}";

        requestBuilder.uri(uri).content(content).status(400).post();
    }
}
