package br.com.fiap.totem_express.domain.product;

import java.math.BigDecimal;


public class Product {

    private Long id;
    private String name;
    private String description;
    private String imagePath;
    private BigDecimal price;
    private Category category;


    public BigDecimal getPrice() {
        return this.price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public Category getCategory() {
        return category;
    }
}
