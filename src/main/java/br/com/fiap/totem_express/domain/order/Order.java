package br.com.fiap.totem_express.domain.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import br.com.fiap.totem_express.domain.user.User;
import br.com.fiap.totem_express.presentation.order.*;

public class Order {
    private Long id;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
    private Set<OrderItem> items = new HashSet<>();
    private BigDecimal total = BigDecimal.ZERO;
    private User user;
    private Status status = Status.RECEIVED;
    
    public Order(LocalDateTime createdAt, LocalDateTime updatedAt, Set<OrderItem> items, User user) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.items = items;
        this.user = user;
        this.setTotal(items);
    }

    public Order(LocalDateTime createdAt, LocalDateTime updatedAt, User user) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.user = user;
    }

    public Order(Set<OrderItem> orderItemsDomain, User user) {
        this.user = user;
        orderItemsDomain.forEach(oi -> oi.setOrder(this));
        this.items = orderItemsDomain;
        this.setTotal(orderItemsDomain);
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

    //TODO ao adicionar item, somar no total
    public void addItem(OrderItem item) {
        items.add(item);
    }

    protected void setTotal(Set<OrderItem> items) {
        this.total = items.stream().map(OrderItem::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
