package br.com.fiap.totem_express.domain.product;

import java.math.BigDecimal;

import org.hibernate.sql.Update;

import br.com.fiap.totem_express.application.product.input.UpdateProductInput;


public class Product {

    private Long id;
    private String name;
    private String description;
    private String imagePath;
    private BigDecimal price;
    private Category category;

    public Product(Long id, String name, String description, String imagePath, BigDecimal price, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
        this.price = price;
        this.category = category;
    }

    public Product(String name, String description, String imagePath, BigDecimal price, Category category) {
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
        this.price = price;
        this.category = category;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public String getName() {
        return this.name;
    }

    public Long getId() {
        return id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public Category getCategory() {
        return category;
    }

    public String getDescription() {
        return this.description;
    }
    
    public void update(UpdateProductInput input){
        this.id = input.id();
        this.name = input.name();
        this.description = input.description();
        this.imagePath = input.imagePath();
        this.price = input.price();
        this.category = input.category();
    }
}
