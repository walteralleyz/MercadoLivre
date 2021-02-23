package br.com.zup.MercadoLivre.category;

public class CategoryResponseDTO {
    private final String name;
    private final CategoryResponseDTO mother;

    public CategoryResponseDTO(String name, CategoryResponseDTO category) {
        this.name = name;
        this.mother = category;
    }

    public String getName() {
        return name;
    }

    public CategoryResponseDTO getMother() {
        return mother;
    }
}
