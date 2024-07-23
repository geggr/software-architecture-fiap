package br.com.fiap.totem_express.application.product.output;

import java.math.BigDecimal;

import br.com.fiap.totem_express.domain.product.Category;

public interface NewProductView {
    Long id();
    String name();
    String description();
    String imagePath();
    BigDecimal price();
    Category category();
    
    record CreatedView(
            Long id,
            String name,
            String description,
            String imagePath,
            BigDecimal price,
            Category category) implements NewProductView {}
}
