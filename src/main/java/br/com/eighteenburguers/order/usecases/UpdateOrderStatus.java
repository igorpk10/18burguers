package br.com.eighteenburguers.order.usecases;

import br.com.eighteenburguers.order.entitys.Order;
import br.com.eighteenburguers.order.entitys.OrderStatus;
import br.com.eighteenburguers.product.exceptions.BusinessException;

public interface UpdateOrderStatus {

	Order execute(Long orderId, OrderStatus status) throws BusinessException;
}
