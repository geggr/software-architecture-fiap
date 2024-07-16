package br.com.fiap.totem_express.domain.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    
    

    public OrderItem(Order order, Product product, Long quantity, BigDecimal price) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.price = product.getPrice();
    }

    public OrderItem(LocalDateTime createdAt, ProductEntity product, OrderEntity order, Long quantity, BigDecimal price) {
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

    
}
