package br.com.fiap.totem_express.domain.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.fiap.totem_express.application.order.output.OrderItemView;
import br.com.fiap.totem_express.domain.product.Product;
import br.com.fiap.totem_express.infrastructure.order.*;
import br.com.fiap.totem_express.infrastructure.product.*;

public class OrderItem {
    private Long id;
    private LocalDateTime createdAt =  LocalDateTime.now();
    private Order order;
    private Product product;
    private Long quantity;
    private BigDecimal price;

    public OrderItem(LocalDateTime createdAt, ProductEntity product, OrderEntity order
            , Long quantity, BigDecimal price) {
        this.createdAt = createdAt;
        this.product = product.toDomain();
        this.order = order.toDomain();// TODO aqui está dando overflow porque se chamam
        this.quantity = quantity;
        this.price = price;//TODO esse aqui é preço unitário ou total?
    }

    public OrderItem(Product product, Long quantity) {
        this.product = product;
        this.quantity = quantity;
        this.price = product.getPrice();
    }

    public Order getOrder() {
        return order;
    }
    public Product getProduct() {
        return product;
    }
    public Long getQuantity() {
        return quantity;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public Long getId() {
        return id;
    }


    public String getProductName() {
        return getProduct().getName();
    }

    public void setOrder(Order order) {
        if(order == null) throw new IllegalArgumentException("Order must not be null");
        this.order = order;
    }
}
