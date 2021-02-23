package br.com.zup.MercadoLivre.payment;

public class PagSeguro implements IPayment {
    @Override
    public String getLink(String id, String url) {
        return String.format("paypal.com/%s?redirectUrl=%s", id, url);
    }
}
