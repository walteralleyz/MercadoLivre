package br.com.zup.MercadoLivre.product;

import br.com.zup.MercadoLivre.exception.DetailsSizeException;

import javax.persistence.EntityManager;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static br.com.zup.MercadoLivre.category.CategoryDTO.findCategoryById;

public class ProductDTO {
    @NotBlank
    private final String name;

    @NotNull
    @Positive
    private final BigDecimal price;

    @Min(value = 0)
    private final Integer quantity;

    @Size(min = 3)
    private final List<Map<String, String>> details;

    @NotBlank
    @Size(max = 1000)
    private final String description;

    @NotNull
    private final Integer category_id;

    public ProductDTO(
        @NotBlank String name,
        @NotNull @Positive BigDecimal price,
        @Min(value = 0) Integer quantity,
        @Size(min = 3) List<Map<String, String>> details,
        @Size(min = 1000) String description,
        @NotNull Integer category_id
    ) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.details = details;
        this.description = description;
        this.category_id = category_id;
    }

    public Product toModel(EntityManager em) {
        return new Product(
          name,
          price,
          quantity,
          verifyDetailsSize(em),
          description,
          findCategoryById(em, category_id)
        );
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

    public List<Map<String, String>> getDetails() {
        return details;
    }

    public String getDescription() {
        return description;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public List<ProductDetails> verifyDetailsSize(EntityManager em) {
        List<ProductDetails> productDetails = new ArrayList<>();

        details.forEach(dt -> {
            if(dt.keySet().size() != 2 || !dt.containsKey("title") || !dt.containsKey("text"))
                throw new DetailsSizeException("details");

            ProductDetails temp = new ProductDetails(dt.get("title"), dt.get("text"));
            em.persist(temp);

            productDetails.add(temp);
        });

        return productDetails;
    }
}
