package br.com.eighteenburguers.core.ports.inbound.order;

import br.com.eighteenburguers.core.domain.Order;
import br.com.eighteenburguers.core.exceptions.BusinessException;

public interface FindOrderByIdInputPort {
    
    Order execute(Long orderId) throws BusinessException;
}
