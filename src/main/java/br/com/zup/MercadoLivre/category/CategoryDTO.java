package br.com.zup.MercadoLivre.category;

import br.com.zup.MercadoLivre.annotation.Singular;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

import static br.com.zup.MercadoLivre.category.Category.findCategoryById;

public class CategoryDTO {

    @NotBlank
    @Singular(domainClass = Category.class, fieldName = "name")
    private final String name;

    private final Integer mother_id;

    public CategoryDTO(String name, Integer mother_id) {
        this.name = name;
        this.mother_id = mother_id;
    }

    public Category toModel(EntityManager em) {
        Category category = new Category(name);

        if(mother_id != null)
            category.setMother(findCategoryById(em, mother_id));

        return category;
    }

    public String getName() {
        return name;
    }

    public Integer getMother_id() {
        return mother_id;
    }
}
