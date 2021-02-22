package br.com.zup.MercadoLivre.product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @PersistenceContext
    private EntityManager em;

    @PostMapping
    @Transactional
    public ResponseEntity<ProductResponseDTO> create(@RequestBody @Valid ProductDTO dto) {
        Product product = dto.toModel(em);
        em.persist(product);

        return ResponseEntity.ok(product.toDTO());
    }
}
