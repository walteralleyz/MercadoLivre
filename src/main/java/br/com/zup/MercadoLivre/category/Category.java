package br.com.zup.MercadoLivre.category;

import javax.persistence.*;

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
        return new CategoryResponseDTO(name, mother);
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
}
