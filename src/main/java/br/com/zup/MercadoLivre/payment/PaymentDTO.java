package br.com.zup.MercadoLivre.payment;

import br.com.zup.MercadoLivre.checkout.Checkout;
import br.com.zup.MercadoLivre.checkout.CheckoutStatus;

import javax.persistence.EntityManager;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

import static br.com.zup.MercadoLivre.checkout.Checkout.findCheckoutById;

public class PaymentDTO {
    @NotBlank
    private final Integer checkout_id;

    @NotBlank
    @Enumerated(value = EnumType.STRING)
    private final PaymentEnum payment;

    @NotBlank
    private final String status;

    public PaymentDTO(Integer checkout_id, PaymentEnum payment, String status) {
        this.checkout_id = checkout_id;
        this.payment = payment;
        this.status = status;
    }

    public Payment toModel(EntityManager em) {
        Checkout checkout = findCheckoutById(em, checkout_id);
        checkout.setStatus(CheckoutStatus.CONCLUIDA);

        return new Payment(
            checkout,
            payment,
            status
        );
    }


    public Integer getCheckout_id() {
        return checkout_id;
    }

    public PaymentEnum getPayment() {
        return payment;
    }

    public String getStatus() {
        return status;
    }
}
