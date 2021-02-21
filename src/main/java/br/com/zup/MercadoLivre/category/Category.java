package br.com.zup.MercadoLivre.category;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category categoryMother;

    @OneToMany(mappedBy = "categoryMother", fetch = FetchType.EAGER)
    private List<Category> categoryChildren;

    @Deprecated
    public Category() {}

    public Category(String name) {
        this.name = name;
    }

    public CategoryResponseDTO toDTO() {
        return new CategoryResponseDTO(name, categoryMother, categoryChildren);
    }

    public void setCategoryMother(Category categoryMother) {
        this.categoryMother = categoryMother;
    }


    public void setCategoryChildren(List<Category> categoryChildren) {
        this.categoryChildren = categoryChildren;
    }

    public Integer getId() {
        return id;
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
