package br.com.fiap.totem_express.infrastructure.order;

import br.com.fiap.totem_express.domain.order.*;
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
        this.quantity = item.getQuantity();
        this.price = item.getTotal();
    }

    public OrderItemEntity(OrderItem item, OrderEntity orderEntity) {
        this(item);
        order = orderEntity;
    }

    public Long getId() {
        return id;
    }

    public OrderItem toDomain() {
        Order orderDomain = this.order.toDomain();
        OrderItem orderItem = new OrderItem(this.createdAt, this.product, this.quantity, this.price);
        orderDomain.addItem(orderItem);
        orderItem.setOrder(orderDomain);
        return orderItem;
    }
}
