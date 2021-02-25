package br.com.zup.MercadoLivre.checkout;

import br.com.zup.MercadoLivre.annotation.QuantityAvailable;
import br.com.zup.MercadoLivre.payment.PaymentEnum;
import br.com.zup.MercadoLivre.product.Product;

import javax.persistence.EntityManager;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@QuantityAvailable
public class CheckoutDTO {
    @NotNull
    private final Integer product_id;

    @NotNull
    @Positive
    private final Integer productQuantity;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private final CheckoutStatus status;

    @NotNull
    private final PaymentEnum payment;

    public CheckoutDTO(
        @NotNull Integer product_id,
        @NotNull @Positive Integer productQuantity,
        @NotNull CheckoutStatus status,
        @NotNull PaymentEnum payment
    ) {
        this.product_id = product_id;
        this.productQuantity = productQuantity;
        this.status = status;
        this.payment = payment;
    }

    public Checkout toModel(EntityManager em) {
        Product product = Product.findProductById(em, product_id);
        product.setQuantity(productQuantity);

        return new Checkout(
            product,
            productQuantity,
            status,
            payment
        );
    }


    public Integer getProduct_id() {
        return product_id;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public CheckoutStatus getStatus() {
        return status;
    }

    public PaymentEnum getPayment() {
        return payment;
    }
}
