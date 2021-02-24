package br.com.zup.MercadoLivre.checkout;

import br.com.zup.MercadoLivre.payment.IPayment;
import br.com.zup.MercadoLivre.payment.PagSeguro;
import br.com.zup.MercadoLivre.payment.Payment;
import br.com.zup.MercadoLivre.payment.Paypal;
import br.com.zup.MercadoLivre.product.Product;
import br.com.zup.MercadoLivre.user.User;

import javax.persistence.*;

@Entity
public class Checkout {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private Product product;

    @Column(nullable = false)
    private Integer productQuantity;

    @OneToOne
    private User client;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private CheckoutStatus status;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Payment payment;

    @Deprecated
    public Checkout() {}

    public Checkout(
        Product product,
        Integer productQuantity,
        CheckoutStatus status,
        Payment payment
    ) {
        this.product = product;
        this.productQuantity = productQuantity;
        this.status = status;
        this.payment = payment;

        this.client = User.getActualUser();
    }

    public Integer getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public User getClient() {
        return client;
    }

    public CheckoutStatus getStatus() {
        return status;
    }

    public Payment getPayment() {
        return payment;
    }

    public IPayment getPaymentConcrete() {
        if(getPayment().toString().equals("PAYPAL"))
            return new Paypal();

        return new PagSeguro();
    }

    public void sendEmailToSeller() {
        System.out.printf("Usuário %s iniciou uma compra do produto %s com status %s.%n",
            client.getUsername(), product.getName(), status
        );
    }

}
