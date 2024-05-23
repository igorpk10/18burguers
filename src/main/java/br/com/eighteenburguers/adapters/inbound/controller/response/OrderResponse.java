package br.com.eighteenburguers.adapters.inbound.controller.response;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import br.com.eighteenburguers.core.domain.OrderStatus;
import lombok.Data;

@Data
public class OrderResponse {
    
    private Long id;
    private Long customerId;
    private BigDecimal amount;
    private OrderStatus status;
    private String statusDescription;
    private List<OrderItemResponse> items;
    private Instant createdAt;
    private Instant updatedAt;

}
