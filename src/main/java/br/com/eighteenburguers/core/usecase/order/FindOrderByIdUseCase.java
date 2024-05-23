package br.com.eighteenburguers.core.usecase.order;

import br.com.eighteenburguers.core.domain.Order;
import br.com.eighteenburguers.core.exceptions.BusinessException;
import br.com.eighteenburguers.core.exceptions.OrderNotFoundException;
import br.com.eighteenburguers.core.ports.inbound.order.FindOrderByIdInputPort;
import br.com.eighteenburguers.core.ports.outbound.order.FindOrderOutputPort;

public class FindOrderByIdUseCase implements FindOrderByIdInputPort {

    private FindOrderOutputPort findOrderOutputPort;

    public FindOrderByIdUseCase(FindOrderOutputPort findOrderOutputPort) {
        this.findOrderOutputPort = findOrderOutputPort;
    }

    @Override
    public Order execute(Long orderId) throws BusinessException {
        return findOrderOutputPort.findById(orderId).orElseThrow(OrderNotFoundException::new);
    }
    
}
