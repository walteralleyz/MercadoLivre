package br.com.zup.MercadoLivre.details;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

public class DetailsDTO {
    @NotBlank
    private final String title;

    @NotBlank
    private final String text;

    public DetailsDTO(@NotBlank String title, @NotBlank String text) {
        this.title = title;
        this.text = text;
    }

    public Details toModel(EntityManager em) {
        Details details = new Details(title, text);
        em.persist(details);

        return details;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
