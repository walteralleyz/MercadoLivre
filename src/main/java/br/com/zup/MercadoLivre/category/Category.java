package br.com.zup.MercadoLivre.category;

import br.com.zup.MercadoLivre.exception.CategoryNotFoundException;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Table(name = "categories")
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category mother;

    @Deprecated
    public Category() {}

    public Category(String name) {
        this.name = name;
    }

    public CategoryResponseDTO toDTO() {
        return new CategoryResponseDTO(name, mother.toDTO());
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMother(Category mother) {
        this.mother = mother;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getMother() {
        return mother;
    }

    public static Category findCategoryById(EntityManager em, int id) {
        return Optional.ofNullable(em.find(Category.class, id))
            .orElseThrow(() -> new CategoryNotFoundException("id"));
    }
}
