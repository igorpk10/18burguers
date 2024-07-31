package br.com.eighteenburguers.order.usecases;

import br.com.eighteenburguers.order.entitys.Order;
import br.com.eighteenburguers.product.exceptions.BusinessException;
import br.com.eighteenburguers.product.exceptions.OrderNotFoundException;
import br.com.eighteenburguers.order.services.FindOrderService;

public class FindOrderByIdUseCaseImpl implements FindOrderByIdUseCase {

    private FindOrderService findOrderService;

    public FindOrderByIdUseCaseImpl(FindOrderService findOrderService) {
        this.findOrderService = findOrderService;
    }

    @Override
    public Order execute(Long orderId) throws BusinessException {
        return findOrderService.findById(orderId).orElseThrow(OrderNotFoundException::new);
    }
}
