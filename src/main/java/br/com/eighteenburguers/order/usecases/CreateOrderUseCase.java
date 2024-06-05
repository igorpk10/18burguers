package br.com.eighteenburguers.order.usecases;

import java.util.List;

import br.com.eighteenburguers.order.entitys.Order;
import br.com.eighteenburguers.order.entitys.OrderItem;
import br.com.eighteenburguers.product.exceptions.BusinessException;

public interface CreateOrderUseCase {
    
    Order execute(final Long customerId, final List<OrderItem> items) throws BusinessException;
}
