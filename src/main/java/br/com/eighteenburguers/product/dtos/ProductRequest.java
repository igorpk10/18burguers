package br.com.eighteenburguers.product.dtos;

import br.com.eighteenburguers.category.model.Category;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequest {

    @NotBlank
    private String name;

    private Category category;

    private BigDecimal price;

    @NotBlank
    private String description;

    @NotBlank
    private String image;
}
