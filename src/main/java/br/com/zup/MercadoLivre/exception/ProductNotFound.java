package br.com.zup.MercadoLivre.exception;

public class ProductNotFound extends GenericException {
    public ProductNotFound(String field) {
        super(field, "Produto não encontrado");
    }
}
