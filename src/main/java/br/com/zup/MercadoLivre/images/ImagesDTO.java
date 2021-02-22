package br.com.zup.MercadoLivre.images;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

public class ImagesDTO {
    @NotBlank
    private String link;

    @Deprecated
    public ImagesDTO() {}

    public ImagesDTO(@NotBlank String link) {
        this.link = link;
    }

    public Images toModel(EntityManager em) {
        Images images = new Images(link);
        em.persist(images);

        return images;
    }

    public String getLink() {
        return link;
    }
}
