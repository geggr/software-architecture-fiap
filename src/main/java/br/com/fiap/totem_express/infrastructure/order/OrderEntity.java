package br.com.fiap.totem_express.infrastructure.order;

import br.com.fiap.totem_express.domain.order.*;
import br.com.fiap.totem_express.domain.user.*;
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
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Set<OrderItemEntity> items = new HashSet<>();
    @NotNull
    private BigDecimal total;

    //TODO substituir pela entidade do User quando estiver feita
    private Long user_id;
    private Status status = Status.RECEIVED;

    @Deprecated
    public OrderEntity() {
    }

    public OrderEntity(Order order) {
        this.id = order.getId();
        this.createdAt = order.getCreatedAt();
        this.updatedAt = order.getUpdatedAt();
        this.total = order.getTotal();
        this.items = order.getItems().stream().map(item -> new OrderItemEntity(item)).collect(Collectors.toSet());
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    //TODO colocar o usuário bonitinho quando implementarem a parte de usuários
    public static Order toDomain(OrderEntity orderEntity) {
        return new Order(
                orderEntity.createdAt,
                orderEntity.updatedAt,
                orderEntity.items.stream().map(OrderItemEntity::toDomain).collect(Collectors.toSet()),
                new User("bla", "ble", "bli")
        );
    }
}
