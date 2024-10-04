package br.com.fiap.totem_express.infrastructure.payment;

import br.com.fiap.totem_express.domain.payment.Payment;
import br.com.fiap.totem_express.domain.payment.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.STRING;

@Entity
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @PastOrPresent
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @NotNull
    @Enumerated(STRING)
    private Status status = Status.PENDING;

    private Long transactionId;

    @Positive
    private BigDecimal amount;

    private String qrCode;

    @Deprecated
    public PaymentEntity() {
    }

    public PaymentEntity(Payment payment) {
        this.id = payment.getId();
        this.createdAt = payment.getCreatedAt();
        this.status = payment.getStatus();
        this.transactionId = payment.getTransactionId();
        this.amount = payment.getAmount();
        this.qrCode = payment.getQrCode();
    }

    public Payment toDomain() {
        return new Payment(id, createdAt, status, transactionId, amount, qrCode);
    }
}
