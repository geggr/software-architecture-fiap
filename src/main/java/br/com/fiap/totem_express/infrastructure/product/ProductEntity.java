package br.com.fiap.totem_express.infrastructure.product;

import br.com.fiap.totem_express.domain.product.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.*;

@Entity
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private BigDecimal price;

    @Deprecated
    public ProductEntity() {}

    public ProductEntity(Product product) {
        this.id = product.getId();
        this.price = product.getPrice();
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
