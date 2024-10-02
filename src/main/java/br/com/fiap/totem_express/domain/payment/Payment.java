package br.com.fiap.totem_express.domain.payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Payment {

    private Long id;
    private LocalDateTime createdAt = LocalDateTime.now();
    private Status status = Status.PENDING;
    private Long transactionId;
    private BigDecimal amount;

    public Payment(Long id, LocalDateTime createdAt, Status status, Long transactionId, BigDecimal amount) {
        this.id = id;
        this.createdAt = createdAt;
        this.status = status;
        this.transactionId = transactionId;
        this.amount = amount;
    }
}
