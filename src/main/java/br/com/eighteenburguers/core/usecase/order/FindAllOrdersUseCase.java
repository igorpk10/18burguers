package br.com.eighteenburguers.core.usecase.order;

import java.util.List;

import br.com.eighteenburguers.core.domain.Order;
import br.com.eighteenburguers.core.ports.inbound.order.FindAllOrdersInputPort;
import br.com.eighteenburguers.core.ports.outbound.order.FindOrderOutputPort;

public class FindAllOrdersUseCase implements FindAllOrdersInputPort {

    private final FindOrderOutputPort findOrderOutputPort;

    public FindAllOrdersUseCase(FindOrderOutputPort findOrderOutputPort) {
        this.findOrderOutputPort = findOrderOutputPort;
    }

    @Override
    public List<Order> execute() {
        return findOrderOutputPort.findAll();
    }
    
}
