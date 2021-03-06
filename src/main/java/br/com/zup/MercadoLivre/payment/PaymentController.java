package br.com.zup.MercadoLivre.payment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @PersistenceContext
    private EntityManager em;

    @GetMapping("/{pay}/{id}/{status}")
    @Transactional
    public ResponseEntity<String> paypal(@PathVariable String pay, @PathVariable Integer id, @PathVariable String status) {
        PaymentDTO dto = new PaymentDTO(id, pay, status);
        Payment payment = dto.toModel(em);
        em.persist(payment);

        comunicarSetorNotaFiscal(payment.getCheckout().getId(), payment.getCheckout().getClient().getId());
        comunicarRankingVendedor(payment.getCheckout().getId(), payment.getCheckout().getProduct().getUser().getId());
        payment.sendEmailToClient();

        return ResponseEntity.ok(payment.toDTO().getStatus());
    }

    public void comunicarSetorNotaFiscal(Integer idCompra, Long idUsuario) {
        System.out.printf("Compra %d realizada pelo usuário %d%n", idCompra, idUsuario);
    }

    public void comunicarRankingVendedor(Integer idCompra, Long idUsuario) {
        System.out.printf("Compra %d do vendedor %d%n", idCompra, idUsuario);
    }
}
