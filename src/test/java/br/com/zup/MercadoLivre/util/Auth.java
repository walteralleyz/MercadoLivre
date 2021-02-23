package br.com.zup.MercadoLivre.util;

import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;

import static br.com.zup.MercadoLivre.util.Token.extractToken;
import static br.com.zup.MercadoLivre.util.Request.performPost;

public class Auth {
    public static String generateToken(MockMvc mvc, String username, String password) throws Exception {
        URI uri = new URI("/api/security");
        String content = String.format("{\"login\": \"%s\", \"password\": \"%s\"}", username, password);

        return extractToken(
            performPost(mvc, uri, content, 200, "test")
        );
    }
}
