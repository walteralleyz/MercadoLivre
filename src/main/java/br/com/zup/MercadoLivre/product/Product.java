package br.com.zup.MercadoLivre.product;

import br.com.zup.MercadoLivre.category.Category;
import br.com.zup.MercadoLivre.details.Details;
import br.com.zup.MercadoLivre.exception.CategoryNotFoundException;
import br.com.zup.MercadoLivre.exception.NotTheSameOwnerException;
import br.com.zup.MercadoLivre.exception.ProductNotFoundException;
import br.com.zup.MercadoLivre.images.Images;
import br.com.zup.MercadoLivre.user.User;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "products")
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer quantity;

    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Details> details;

    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Images> images;

    @OneToOne
    @LazyCollection(LazyCollectionOption.FALSE)
    private User user;

    @Column(length = 1000, nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    private final LocalDate createdAt = LocalDate.now();

    @Deprecated
    public Product() {}

    public Product(
        String name,
        BigDecimal price,
        Integer quantity,
        List<Details> details,
        String description,
        Category category,
        List<Images> images
    ) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.details = details;
        this.description = description;
        this.category = category;
        this.images = images;
        this.user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public ProductResponseDTO toDTO() {
        return new ProductResponseDTO(
            name,
            price,
            quantity,
            details,
            images,
            description,
            category,
            createdAt
        );
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setImages(List<Images> images) {
        this.images = images;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public List<Details> getDetails() {
        return details;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public List<Images> getImages() {
        return images;
    }

    public User getUser() {
        return user;
    }

    public static void verifySameOwner(User productUser) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(!user.getUsername().equals(productUser.getUsername()))
            throw new NotTheSameOwnerException("username");
    }

    public static Product findProductById(EntityManager em, int id) {
        return Optional.ofNullable(em.find(Product.class, id))
            .orElseThrow(() -> new ProductNotFoundException("id"));
    }
}
