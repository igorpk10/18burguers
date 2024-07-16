package br.com.eighteenburguers.order.usecases;

import java.util.List;

import br.com.eighteenburguers.order.entitys.Order;
import br.com.eighteenburguers.order.services.FindOrderService;

public class FindAllOrdersUseCaseImpl implements FindAllOrdersUseCase {

    private final FindOrderService findOrderOutputPort;

    public FindAllOrdersUseCaseImpl(FindOrderService findOrderOutputPort) {
        this.findOrderOutputPort = findOrderOutputPort;
    }

    @Override
    public List<Order> execute() {
        return findOrderOutputPort.findAll();
    }
    
}
