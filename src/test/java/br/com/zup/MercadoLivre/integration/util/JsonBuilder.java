package br.com.zup.MercadoLivre.integration.util;

import java.util.List;
import java.util.Map;

public class JsonBuilder {
    private final StringBuilder json;

    public JsonBuilder() {
        json = new StringBuilder();
        json.append("{");
    }

    public JsonBuilder property(String field, String attribute) {
        json.append(String.format("\"%s\": \"%s\",", field, attribute));
        return this;
    }

    public JsonBuilder property(String field, Integer attribute) {
        json.append(String.format("\"%s\": %d,", field, attribute));
        return this;
    }

    public JsonBuilder property(String field, String[] attribute) {
        StringBuilder temp = new StringBuilder();
        temp.append("[");

        for(String attr : attribute) {
            temp.append(attr);
        }

        temp.append("]");

        json.append(String.format("\"%s\": %s,", field, temp));
        return this;
    }

    public String compact() {
        int index = json.lastIndexOf(",");
        json.deleteCharAt(index);
        json.append("}");
        return json.toString();
    }
}
