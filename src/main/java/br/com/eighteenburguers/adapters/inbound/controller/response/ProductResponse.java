package br.com.eighteenburguers.adapters.inbound.controller.response;

import br.com.eighteenburguers.core.enums.Category;
import lombok.Builder;
import lombok.Data;

import java.io.File;
import java.math.BigDecimal;

@Data
@Builder
public class ProductResponse {

    private Long id;

    private String name;

    private Category category;

    private BigDecimal price;

    private String description;

    private File image;
}
