package br.com.zup.MercadoLivre.product;

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
public class ProductTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName(value = "login and save product")
    public void shouldLoginAndSaveAProduct() throws Exception {
        URI uri = new URI("/api/security");
        String content = "{\"login\": \"user@mail.com\", \"password\": \"123456\"}";

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

        String token = performPost(uri, content, 200, "test");
        String response = performPost(product, productContent, 200, extractToken(token));

        System.out.println(response);
    }

    public String performPost(URI uri, String content, int status, String h) throws Exception {
        return mvc.perform(MockMvcRequestBuilders
            .post(uri)
            .content(content)
            .header("Accept-language", "pt")
            .header("Authorization", "Bearer " + h)
            .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(MockMvcResultMatchers.status().is(status))
        .andReturn().getResponse().getContentAsString();
    }

    public String extractToken(String token) {
        String splitted = token.split(",")[0];

        return splitted.split(":")[1].replaceAll("\"", "");
    }
}
