package br.com.fiap.totem_express.domain.payment;

import br.com.fiap.totem_express.shared.invariant.Invariant;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static br.com.fiap.totem_express.shared.invariant.Rule.notNull;

public class Payment {

    private Long id;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;
    private Status status = Status.PENDING;
    private Long transactionId;
    private BigDecimal amount;
    private String qrCode;

    public Payment(Long id, LocalDateTime createdAt, Status status, Long transactionId, BigDecimal amount, String qrCode) {
        this.id = id;
        this.createdAt = createdAt;
        this.status = status;
        this.transactionId = transactionId;
        this.amount = amount;
        this.qrCode = qrCode;
    }

    public Payment(Status status, BigDecimal amount) {
        this.status = status;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Status getStatus() {
        return status;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public void processPayment(Status status) {
        Invariant.of(status, notNull("Payment status must be not null"));
        this.status = status;
        this.updatedAt = LocalDateTime.now();
    }
}
