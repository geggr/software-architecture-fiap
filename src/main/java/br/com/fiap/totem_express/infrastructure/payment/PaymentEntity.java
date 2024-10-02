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

    @NotNull
    private Long transactionId;

    @Positive
    private BigDecimal amount;

    @Deprecated
    public PaymentEntity() {
    }

    public Payment toDomain() {
        return new Payment(id, createdAt, status, transactionId, amount);
    }
}
