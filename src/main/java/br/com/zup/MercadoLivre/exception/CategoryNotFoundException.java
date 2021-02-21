package br.com.zup.MercadoLivre.exception;

public class CategoryNotFoundException extends RuntimeException {
    private final String field;

    public CategoryNotFoundException(String f) {
        super("Categoria não encontrada!");

        this.field = f;
    }

    public String getField() {
        return field;
    }
}
