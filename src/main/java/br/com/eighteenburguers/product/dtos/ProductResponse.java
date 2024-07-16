package br.com.eighteenburguers.product.dtos;

import br.com.eighteenburguers.category.model.Category;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductResponse {

    private Long id;

    private String name;

    private Category category;

    private BigDecimal price;

    private String description;

    private String image;
}
