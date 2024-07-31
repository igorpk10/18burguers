package br.com.eighteenburguers.order.dtos;

import java.math.BigDecimal;

import br.com.eighteenburguers.category.model.Category;
import lombok.Data;

@Data
public class OrderItemResponse {
    
    private Long id;

    private String name;

    private Category category;

    private BigDecimal price;

    private Integer quantity;

    private String observation;
}
