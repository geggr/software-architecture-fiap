package br.com.fiap.totem_express.domain.payment;

import java.time.LocalDateTime;

import br.com.fiap.totem_express.domain.order.Order;

public class Payment {
    private Long id;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
    private Order order;
    private Status status = Status.PENDING;
    private String transactionId;
    
}
