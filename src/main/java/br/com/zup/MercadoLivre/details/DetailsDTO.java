package br.com.zup.MercadoLivre.details;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static br.com.zup.MercadoLivre.product.Product.findProductById;

public class DetailsDTO {
    @NotBlank
    private final String title;

    @NotBlank
    private final String text;

    @NotNull
    private final Integer product_id;

    public DetailsDTO(@NotBlank String title, @NotBlank String text, @NotNull Integer product_id) {
        this.title = title;
        this.text = text;
        this.product_id = product_id;
    }

    public Details toModel(EntityManager em) {
        Details details = new Details(title, text, findProductById(em, product_id));
        em.persist(details);

        return details;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public Integer getProduct_id() {
        return product_id;
    }
}
