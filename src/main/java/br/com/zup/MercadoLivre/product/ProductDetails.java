package br.com.zup.MercadoLivre.product;

import javax.persistence.*;

@Entity
@Table(name = "details")
public class ProductDetails {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String text;

    @Deprecated
    public ProductDetails() {}

    public ProductDetails(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
