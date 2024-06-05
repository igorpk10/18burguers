package br.com.eighteenburguers.order.dtos;

import java.util.List;

import lombok.Data;

@Data
public class OrderRequest {
    
    private Long customerId;
    private List<OrderItemRequest> items;
}
