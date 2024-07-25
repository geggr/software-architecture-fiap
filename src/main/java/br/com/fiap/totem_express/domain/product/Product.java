package br.com.fiap.totem_express.domain.product;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.fiap.totem_express.application.product.input.UpdateProductInput;


public class Product {

    private Long id;
    private String name;
    private String description;
    private String imagePath;
    private BigDecimal price;
    private Category category;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;

    public Product(String name, String description, String imagePath, BigDecimal price, Category category) {
        validateFields(name, description, imagePath, price, category);
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
        this.price = price;
        this.category = category;
    }

    public Product(Long id, String name, String description, String imagePath, BigDecimal price, Category category) {
        this(name, description, imagePath, price, category);
        this.id = id;
    }
    public Product(Long id, String name, String description, String imagePath, BigDecimal price, Category category, LocalDateTime updatedAt) {
        this(name, description, imagePath, price, category);
        this.id = id;
        this.updatedAt = updatedAt;
    }

    public Product(Long id, String name, String description, String imagePath, BigDecimal price, Category category, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(name, description, imagePath, price, category);
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public void update(UpdateProductInput input) {
        this.id = input.id();
        this.name = input.name();
        this.description = input.description();
        this.imagePath = input.imagePath();
        this.price = input.price();
        this.category = input.category();
        this.updatedAt = LocalDateTime.now();
    }

    private void validateFields(String name, String description, String imagePath, BigDecimal price, Category category) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Name cannot be null or blank");
        if (description == null || description.isBlank())
            throw new IllegalArgumentException("Description cannot be null or blank");
        if (imagePath == null || imagePath.isBlank())
            throw new IllegalArgumentException("ImagePath cannot be null or blank");
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Price must be positive");
        if (category == null) throw new IllegalArgumentException("Category cannot be null");
    }
}
