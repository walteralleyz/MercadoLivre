package br.com.zup.MercadoLivre.rating;

import br.com.zup.MercadoLivre.product.ProductResponseDTO;
import br.com.zup.MercadoLivre.user.UserDTO;

public class RatingResponseDTO {
    private final Integer level;
    private final String title;
    private final String description;
    private final UserDTO user;
    private final ProductResponseDTO product;

    public RatingResponseDTO(Integer level, String title, String description, UserDTO user, ProductResponseDTO product) {
        this.level = level;
        this.title = title;
        this.description = description;
        this.user = user;
        this.product = product;
    }

    public Integer getLevel() {
        return level;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public UserDTO getUser() {
        return user;
    }

    public ProductResponseDTO getProduct() {
        return product;
    }
}
