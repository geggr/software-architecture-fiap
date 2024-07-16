package br.com.fiap.totem_express.infrastructure.order;

import br.com.fiap.totem_express.domain.order.*;
import br.com.fiap.totem_express.domain.product.*;
import br.com.fiap.totem_express.infrastructure.product.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.*;
import java.time.*;

@Entity
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDateTime createdAt =  LocalDateTime.now();

    @ManyToOne(cascade = CascadeType.ALL)
    private OrderEntity order;

    @ManyToOne
    private ProductEntity product;

    @Min(1)
    private Long quantity;

    @NotNull
    private BigDecimal price;

    @Deprecated
    public OrderItemEntity() {
    }

    public OrderItemEntity(OrderItem item) {
        this.id = item.getId();
        this.createdAt = item.getCreatedAt();
        this.product = new ProductEntity(item.getProduct());
        this.order = new OrderEntity(item.getOrder());
        this.quantity = item.getQuantity();
        this.price = item.getPrice();
    }

    public Long getId() {
        return id;
    }
}
