package br.com.eighteenburguers.core.domain;

import br.com.eighteenburguers.core.enums.CategoryEnum;

import java.awt.image.BufferedImage;
import java.math.BigDecimal;

public class Product {
    private Long id;
    private String name;
    private CategoryEnum categoryEnum;
    private BigDecimal price;
    private String description;
    private BufferedImage image;

    public Product(String name, CategoryEnum categoryEnum, BigDecimal price, String description, BufferedImage image) {
        this.name = name;
        this.categoryEnum = categoryEnum;
        this.price = price;
        this.description = description;
        this.image = image;
    }

    public Long getId() { return id;}

    public void setId(Long id) { this.id = id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryEnum getCategoryEnum() {
        return categoryEnum;
    }

    public void setCategory(CategoryEnum categoryEnum) {
        this.categoryEnum = categoryEnum;
    }

    public BigDecimal getPrice() { return price;}

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
