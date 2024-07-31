package br.com.eighteenburguers.order.dtos;

import lombok.Data;

@Data
public class OrderItemRequest {
    
    private Long productId;
    private Integer quantity;
    private String observation;
}
