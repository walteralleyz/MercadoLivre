package br.com.zup.MercadoLivre.product;

import br.com.zup.MercadoLivre.category.Category;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "products")
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer quantity;

    @OneToOne
    private ProductDetails details;

    @Column(length = 1000, nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    private final LocalDate createdAt = LocalDate.now();

    @Deprecated
    public Product() {}

    private Product(
        String name,
        BigDecimal price,
        Integer quantity,
        ProductDetails details,
        String description,
        Category category
    ) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.details = details;
        this.description = description;
        this.category = category;
    }

    public static Product nu(
        String name,
        BigDecimal price,
        Integer quantity,
        ProductDetails details,
        String description,
        Category category
    ) {
        return new Product(name, price, quantity, details, description, category);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public ProductDetails getDetails() {
        return details;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }
}
