package br.com.zup.MercadoLivre.util;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

public class Request {
    public static String performPost(MockMvc mvc, URI uri, String content, int status, String h) throws Exception {
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

    public static String performPut(MockMvc mvc, URI uri, String content, int status, String h) throws Exception {
        return mvc.perform(MockMvcRequestBuilders
            .put(uri)
            .content(content)
            .header("Accept-language", "pt")
            .header("Authorization", "Bearer " + h)
            .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().is(status))
            .andReturn().getResponse().getContentAsString();
    }

    public static String performGet(MockMvc mvc, URI uri, int status, String h) throws Exception {
        return mvc.perform(MockMvcRequestBuilders
            .get(uri)
            .header("Accept-language", "pt")
            .header("Authorization", "Bearer " + h)
            .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().is(status))
            .andReturn().getResponse().getContentAsString();
    }
}
