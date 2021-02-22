package br.com.zup.MercadoLivre.category;

public class CategoryResponseDTO {
    private final String name;
    private final Category mother;

    public CategoryResponseDTO(String name, Category category) {
        this.name = name;
        this.mother = category;
    }

    public String getName() {
        return name;
    }

    public Category getMother() {
        return mother;
    }
}
