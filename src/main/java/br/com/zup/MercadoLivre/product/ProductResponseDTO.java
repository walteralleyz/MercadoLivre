package br.com.zup.MercadoLivre.product;

import br.com.zup.MercadoLivre.category.Category;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ProductResponseDTO {
    private final String name;
    private final BigDecimal price;
    private final Integer quantity;
    private final List<ProductDetails> details;
    private final String description;
    private final Category category;
    private final LocalDate createdAt;

    public ProductResponseDTO(
        String name,
        BigDecimal price,
        Integer quantity,
        List<ProductDetails> details,
        String description,
        Category category,
        LocalDate createdAt
    ) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.details = details;
        this.description = description;
        this.category = category;
        this.createdAt = createdAt;
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
