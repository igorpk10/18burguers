package br.com.eighteenburguers.adapters.inbound.controller.response;

import br.com.eighteenburguers.core.enums.CategoryEnum;
import lombok.Data;

import java.io.File;
import java.math.BigDecimal;

@Data
public class ProductResponse {

    private String name;

    private CategoryEnum categoryEnum;

    private BigDecimal price;

    private String description;

    private File image;
}
