package br.com.zup.MercadoLivre.integration.util;

public class JsonBuilder {
    private StringBuilder json;

    public JsonBuilder() {
        setup();
    }

    private void setup() {
        json = new StringBuilder();
        json.append("{");
    }

    private void close(StringBuilder s, String c) {
        int index = s.lastIndexOf(",");
        s.deleteCharAt(index);
        s.append(c);
    }

    public JsonBuilder property(String field, String attribute) {
        json.append(String.format("\"%s\": \"%s\",", field, attribute));
        return this;
    }

    public JsonBuilder property(String field, Integer attribute) {
        json.append(String.format("\"%s\": %d,", field, attribute));
        return this;
    }

    public JsonBuilder property(String field, String... attribute) {
        StringBuilder temp = new StringBuilder();
        temp.append("[");

        for(String attr : attribute) {
            JsonBuilder jsonBuilder = new JsonBuilder();

            String[] splatted = attr.split(",");

            for(String s : splatted) {
                String[] property = s.split(":");
                jsonBuilder.property(property[0], property[1]);
            }

            temp.append(jsonBuilder.compact());
            temp.append(",");
        }

        close(temp, "]");

        json.append(String.format("\"%s\": %s,", field, temp));
        return this;
    }

    public String compact() {
        close(json, "}");
        String temp = json.toString();
        setup();

        return temp;
    }
}
