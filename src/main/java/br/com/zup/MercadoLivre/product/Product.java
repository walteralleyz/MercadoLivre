package br.com.zup.MercadoLivre.product;

import br.com.zup.MercadoLivre.category.Category;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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

    @OneToMany
    private List<ProductDetails> details;

    @Column(length = 1000, nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    private final LocalDate createdAt = LocalDate.now();

    @Deprecated
    public Product() {}

    public Product(
        String name,
        BigDecimal price,
        Integer quantity,
        List<ProductDetails> details,
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

    public ProductResponseDTO toDTO() {
        return new ProductResponseDTO(
            name,
            price,
            quantity,
            details,
            description,
            category,
            createdAt
        );
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

    public List<ProductDetails> getDetails() {
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
