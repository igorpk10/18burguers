package br.com.eighteenburguers.core.ports.inbound.order;

import java.util.List;

import br.com.eighteenburguers.core.domain.Order;
import br.com.eighteenburguers.core.domain.OrderItem;
import br.com.eighteenburguers.core.exceptions.BusinessException;

public interface CreateOrderInputPort {
    
    Order execute(final Long customerId, final List<OrderItem> items) throws BusinessException;
}
