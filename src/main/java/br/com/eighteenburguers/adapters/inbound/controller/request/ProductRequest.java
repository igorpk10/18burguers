package br.com.eighteenburguers.adapters.inbound.controller.request;

import br.com.eighteenburguers.core.enums.Category;
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
