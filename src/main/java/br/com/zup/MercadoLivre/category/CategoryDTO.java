package br.com.zup.MercadoLivre.category;

import br.com.zup.MercadoLivre.annotation.Singular;
import br.com.zup.MercadoLivre.exception.CategoryNotFoundException;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryDTO {

    @NotBlank
    @Singular(domainClass = Category.class, fieldName = "name")
    private final String name;

    private final Integer mother_id;
    private final List<Integer> children_id;

    public CategoryDTO(String name, Integer mother_id, List<Integer> children_id) {
        this.name = name;
        this.mother_id = mother_id;
        this.children_id = children_id;
    }

    public Category toModel(EntityManager em) {
        Category category = new Category(name);

        if(mother_id != null)
            category.setCategoryMother(findCategoryById(em, mother_id));

        if(children_id != null)
            retrieveChildren(em, category, children_id);

        return category;
    }

    public String getName() {
        return name;
    }

    public Integer getMother_id() {
        return mother_id;
    }

    public List<Integer> getChildren_id() {
        return children_id;
    }

    public void retrieveChildren(EntityManager em, Category category, List<Integer> ids) {
        List<Category> children = new ArrayList<>();

        ids.forEach(id -> children.add(findCategoryById(em, id)));

        category.setCategoryChildren(children);
    }

    public Category findCategoryById(EntityManager em, int id) {
        return Optional.ofNullable(em.find(Category.class, id))
            .orElseThrow(() -> new CategoryNotFoundException("id"));
    }
}
