package br.com.zup.MercadoLivre.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;

import static br.com.zup.MercadoLivre.util.Request.performPost;

@SpringBootTest
@AutoConfigureMockMvc
public class UserTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName(value = "Cadastrar usuario")
    public void deveCadastrarUmUsuario() throws Exception {
        URI uri = new URI("/api/user");
        String content = "{\"login\": \"visitor@mail.com\", \"password\": \"123456\", \"createdAt\": \"2021-02-02\"}";

        performPost(mvc, uri, content, 200, "test");
    }

    @Test
    @DisplayName(value = "Email repetido")
    public void deveNegarOCadastroComEmailRepetido() throws Exception {
        URI uri = new URI("/api/user");
        String content = "{\"login\": \"user@mail.com\", \"password\": \"123456\", \"createdAt\": \"2021-02-02\"}";

        performPost(mvc, uri, content, 400, "test");
    }

    @Test
    @DisplayName(value = "Email incorreto")
    public void deveRetornarErroSeEmailIncorreto() throws Exception {
        URI uri = new URI("/api/user");
        String content = "{\"login\": \"mail.com\", \"password\": \"123456\", \"createdAt\": \"2021-02-02\"}";

        performPost(mvc, uri, content, 400, "test");
    }

    @Test
    @DisplayName(value = "Senha fora do padrão")
    public void deveRetornarErroSenhaIncorreta() throws Exception {
        URI uri = new URI("/api/user");
        String content = "{\"login\": \"user@mail.com\", \"password\": \"1234\", \"createdAt\": \"2021-02-02\"}";

        performPost(mvc, uri, content, 400, "test");
    }
}
