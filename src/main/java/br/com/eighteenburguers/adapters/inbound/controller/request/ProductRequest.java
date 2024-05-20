package br.com.eighteenburguers.adapters.inbound.controller.request;

import br.com.eighteenburguers.core.enums.CategoryEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.File;
import java.math.BigDecimal;

@Data
public class ProductRequest {

    @NotBlank
    private String name;

    @NotBlank
    private CategoryEnum categoryEnum;

    @NotBlank
    private BigDecimal price;

    @NotBlank
    private String description;

    @NotBlank
    private File image;
}
