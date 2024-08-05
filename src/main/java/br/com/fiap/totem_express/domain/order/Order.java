package br.com.fiap.totem_express.domain.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import br.com.fiap.totem_express.domain.user.User;
import br.com.fiap.totem_express.shared.invariant.Invariant;

import static br.com.fiap.totem_express.shared.invariant.Rule.notEmpty;
import static br.com.fiap.totem_express.shared.invariant.Rule.notNull;

public class Order {
    private Long id;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
    private Set<OrderItem> items = new HashSet<>();
    private BigDecimal total = BigDecimal.ZERO;
    private User user;
    private Status status = Status.RECEIVED;
    
    public Order(LocalDateTime createdAt, LocalDateTime updatedAt, Set<OrderItem> items, User user) {
        Invariant.of(createdAt, notNull("Order created at must be not null"));
        Invariant.of(updatedAt, notNull("Order updated at must be not null"));
        Invariant.of(items, notEmpty("Order item must be be not empty"));

        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.items = items;
        this.user = user;
        this.setTotal(items);
    }

    public Order(LocalDateTime createdAt, LocalDateTime updatedAt, Optional<User> user) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.user = user.orElse(null);
    }

    public Order(Set<OrderItem> orderItemsDomain, Optional<User> user) {
        this.user = user.orElse(null);
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

    public Optional<User> getPossibleUser() {
        return Optional.ofNullable(user);
    }

    public Status getStatus() {
        return status;
    }

    //TODO ao adicionar item, somar no total
    public void addItem(OrderItem item) {
        items.add(item);
    }

    protected void setTotal(Set<OrderItem> items) {
        this.total = items.stream().map(OrderItem::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
