package br.com.zup.MercadoLivre.exception;

public class NotTheSameOwnerException extends GenericException {
    public NotTheSameOwnerException(String field) {
        super(field, "Você não é o dono desse produto, por isso não pode modificá-lo");
    }
}
