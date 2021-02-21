package br.com.zup.MercadoLivre.category;

import java.util.List;

public class CategoryResponseDTO {
    private final String name;
    private final Category categoryMother;
    private final List<Category> categoryChildren;

    public CategoryResponseDTO(String name, Category category, List<Category> categories) {
        this.name = name;
        this.categoryMother = category;
        this.categoryChildren = categories;
    }

    public String getName() {
        return name;
    }

    public List<Category> getCategoryChildren() {
        return categoryChildren;
    }

    public Category getCategoryMother() {
        return categoryMother;
    }
}
