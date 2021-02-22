package br.com.zup.MercadoLivre.product;

import br.com.zup.MercadoLivre.exception.ProductNotFound;
import br.com.zup.MercadoLivre.images.Images;
import br.com.zup.MercadoLivre.images.ImagesDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
            .orElseThrow(() -> new ProductNotFound("id"));

        product = dto.toModel(product, em);

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<ProductResponseDTO> getById(@PathVariable Integer id) {
        Product product = Optional.ofNullable(em.find(Product.class, id))
            .orElseThrow(() -> new ProductNotFound("id"));

        return ResponseEntity.ok(product.toDTO());
    }
}
