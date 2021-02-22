package br.com.zup.MercadoLivre.images;

import javax.persistence.*;

@Entity
@Table(name = "images")
public class Images {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String link;

    @Deprecated
    public Images() {}

    public Images(String link) {
        this.link = link;
    }

    public ImagesDTO toDTO() {
        return new ImagesDTO(link);
    }

    public Integer getId() {
        return id;
    }

    public String getLink() {
        return link;
    }
}
