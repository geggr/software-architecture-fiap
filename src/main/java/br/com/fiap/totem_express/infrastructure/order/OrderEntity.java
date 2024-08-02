package br.com.fiap.totem_express.infrastructure.order;

import br.com.fiap.totem_express.domain.order.*;
import br.com.fiap.totem_express.domain.user.*;
import br.com.fiap.totem_express.infrastructure.user.*;
import jakarta.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.*;
import java.time.*;
import java.util.*;
import java.util.stream.*;

@Entity
public class OrderEntity {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private LocalDateTime createdAt = LocalDateTime.now();
    @NotNull
    private LocalDateTime updatedAt = LocalDateTime.now();
    @NotEmpty
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private Set<OrderItemEntity> items = new HashSet<>();
    @NotNull
    private BigDecimal total;
    @ManyToOne
    @Nullable
    private UserEntity user;
    @Enumerated(EnumType.STRING)
    private Status status = Status.RECEIVED;

    @Deprecated
    public OrderEntity() {
    }

    public OrderEntity(Order order) {
        this.createdAt = order.getCreatedAt();
        this.updatedAt = order.getUpdatedAt();
        this.total = order.getTotal();
        this.user = order.getPossibleUser().map(UserEntity::new).orElse(null);
        this.items = order.getItems().stream().map(item -> new OrderItemEntity(item, this)).collect(Collectors.toSet());
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Set<OrderItemEntity> getItems() {
        return items;
    }

    public Order toDomain() {
        return new Order(
                createdAt,
                updatedAt,
                Optional.ofNullable(user).map(UserEntity::toDomain)
        );
    }
}
