package br.com.fiap.totem_express.presentation.payment;

import br.com.fiap.totem_express.application.payment.impl.CheckPaymentStatusUseCase;
import br.com.fiap.totem_express.application.payment.output.PaymentView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController implements PaymentDocumentation{

    private final CheckPaymentStatusUseCase checkPaymentStatusUseCase;

    public PaymentController(CheckPaymentStatusUseCase checkPaymentStatusUseCase) {
        this.checkPaymentStatusUseCase = checkPaymentStatusUseCase;
    }

    @Override
    @GetMapping("/api/payment/{id}")
    public ResponseEntity<PaymentView> checkPaymentStatus(Long id) {
        PaymentView check = checkPaymentStatusUseCase.checkStatus(id);
        return ResponseEntity.ok(check);
    }
}
