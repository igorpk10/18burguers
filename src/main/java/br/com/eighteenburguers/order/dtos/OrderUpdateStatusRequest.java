package br.com.eighteenburguers.order.dtos;

import br.com.eighteenburguers.order.entitys.OrderStatus;
import lombok.Data;

@Data
public class OrderUpdateStatusRequest {

	private OrderStatus status;
}
