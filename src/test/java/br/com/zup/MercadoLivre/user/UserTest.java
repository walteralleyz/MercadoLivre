package br.com.zup.MercadoLivre.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

@SpringBootTest
@AutoConfigureMockMvc
public class UserTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName(value = "Cadastrar usuario")
    public void deveCadastrarUmUsuario() throws Exception {
        URI uri = new URI("/api/user");
        String content = "{\"login\": \"user@mail.com\", \"password\": \"123456\", \"createdAt\": \"2021-02-02\"}";

        performPost(uri, content, 200);
    }

    @Test
    @DisplayName(value = "Email repetido")
    public void deveNegarOCadastroComEmailRepetido() throws Exception {
        URI uri = new URI("/api/user");
        String content = "{\"login\": \"user@mail.com\", \"password\": \"123456\", \"createdAt\": \"2021-02-02\"}";

        performPost(uri, content, 400);
    }

    @Test
    @DisplayName(value = "Email incorreto")
    public void deveRetornarErroSeEmailIncorreto() throws Exception {
        URI uri = new URI("/api/user");
        String content = "{\"login\": \"mail.com\", \"password\": \"123456\", \"createdAt\": \"2021-02-02\"}";

        performPost(uri, content, 400);
    }

    @Test
    @DisplayName(value = "Senha fora do padrão")
    public void deveRetornarErroSenhaIncorreta() throws Exception {
        URI uri = new URI("/api/user");
        String content = "{\"login\": \"user@mail.com\", \"password\": \"1234\", \"createdAt\": \"2021-02-02\"}";

        performPost(uri, content, 400);
    }

    public void performPost(URI uri, String content, int status) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
            .post(uri)
            .content(content)
            .header("Accept-language", "pt")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().is(status));
    }
}
