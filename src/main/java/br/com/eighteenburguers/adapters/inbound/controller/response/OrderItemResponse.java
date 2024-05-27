package br.com.eighteenburguers.adapters.inbound.controller.response;

import java.math.BigDecimal;

import br.com.eighteenburguers.core.enums.Category;
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
