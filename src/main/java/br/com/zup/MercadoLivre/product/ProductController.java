package br.com.zup.MercadoLivre.product;

import br.com.zup.MercadoLivre.exception.ProductNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

import static br.com.zup.MercadoLivre.product.Product.verifySameOwner;

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

    @PutMapping("/{id}/images")
    @Transactional
    public ResponseEntity<ProductImagesDTO> updateProductImages(
        @PathVariable Integer id,
        @RequestBody @Valid ProductImagesDTO dto
    ) {
        Product product = Optional.ofNullable(em.find(Product.class, id))
            .orElseThrow(() -> new ProductNotFoundException("id"));

        verifySameOwner(product.getUser());

        product = dto.toModel(product, em);

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<ProductResponseDTO> getById(@PathVariable Integer id) {
        Product product = Optional.ofNullable(em.find(Product.class, id))
            .orElseThrow(() -> new ProductNotFoundException("id"));

        return ResponseEntity.ok(product.toDTO());
    }
}
