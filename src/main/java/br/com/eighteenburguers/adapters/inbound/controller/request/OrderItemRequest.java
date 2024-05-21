package br.com.eighteenburguers.adapters.inbound.controller.request;

import lombok.Data;

@Data
public class OrderItemRequest {
    
    private Long productId;
    private Integer quantity;
}
