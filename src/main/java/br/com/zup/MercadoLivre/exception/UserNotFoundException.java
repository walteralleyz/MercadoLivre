package br.com.zup.MercadoLivre.exception;

public class UserNotFoundException extends RuntimeException {
    private final String field;

    public UserNotFoundException(String f) {
        super("User not found!");

        this.field = f;
    }

    public String getField() {
        return field;
    }
}
