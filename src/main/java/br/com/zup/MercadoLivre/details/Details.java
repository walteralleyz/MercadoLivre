package br.com.zup.MercadoLivre.details;

import javax.persistence.*;

@Entity
@Table(name = "details")
public class Details {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String text;

    @Deprecated
    public Details() {}

    public Details(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public DetailsDTO toDTO() {
        return new DetailsDTO(title, text);
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
