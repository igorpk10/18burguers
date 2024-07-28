package br.com.eighteenburguers.order.usecases;

import java.util.List;

import br.com.eighteenburguers.order.entitys.Order;
import br.com.eighteenburguers.order.services.FindOrderService;

public class FindAllOrdersUseCaseImpl implements FindAllOrdersUseCase {

    private final FindOrderService findOrderService;

    public FindAllOrdersUseCaseImpl(FindOrderService findOrderService) {
        this.findOrderService = findOrderService;
    }

    @Override
    public List<Order> execute() {
        return findOrderService.findAll();
    }
    
}
