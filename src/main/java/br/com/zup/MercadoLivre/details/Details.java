package br.com.zup.MercadoLivre.details;

import br.com.zup.MercadoLivre.product.Product;

import javax.persistence.*;

@Entity
@Table(name = "details")
public class Details {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String text;

    @ManyToOne
    private Product product;

    @Deprecated
    public Details() {}

    public Details(String title, String text, Product product) {
        this.title = title;
        this.text = text;
        this.product = product;
    }

    public DetailsDTO toDTO() {
        return new DetailsDTO(title, text, product.getId());
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Product getProduct() {
        return product;
    }

    public String getText() {
        return text;
    }
}
