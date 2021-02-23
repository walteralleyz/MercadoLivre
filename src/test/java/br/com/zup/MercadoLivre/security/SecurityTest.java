package br.com.zup.MercadoLivre.security;

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

import static br.com.zup.MercadoLivre.util.Request.performPost;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName(value = "Login com senha")
    public void deveriaRetornarStatus200ComToken() throws Exception {
        URI uri = new URI("/api/security");
        String content = "{\"login\": \"user@mail.com\", \"password\": \"123456\"}";

        performPost(mvc, uri, content, 200, "test");
    }
}
