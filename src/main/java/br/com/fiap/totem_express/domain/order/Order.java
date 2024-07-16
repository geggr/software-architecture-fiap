package br.com.fiap.totem_express.domain.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import br.com.fiap.totem_express.domain.user.User;

public class Order {
    private Long id;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
    private Set<OrderItem> items = new HashSet<>();
    private BigDecimal total;
    private User user;
    private Status status = Status.RECEIVED;
    
    public Order(LocalDateTime createdAt, LocalDateTime updatedAt, Set<OrderItem> items, User user) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.items = items;
        this.user = user;
        this.total = items.stream().map(OrderItem::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Set<OrderItem> getItems() {
        return items;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public User getUser() {
        return user;
    }

    public Status getStatus() {
        return status;
    }
}
