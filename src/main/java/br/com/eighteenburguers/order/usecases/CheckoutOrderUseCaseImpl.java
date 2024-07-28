package br.com.eighteenburguers.order.usecases;

import br.com.eighteenburguers.order.entitys.Order;
import br.com.eighteenburguers.order.entitys.OrderStatus;
import br.com.eighteenburguers.product.exceptions.BusinessException;
import br.com.eighteenburguers.product.exceptions.OrderNotFoundException;
import br.com.eighteenburguers.order.services.FindOrderService;
import br.com.eighteenburguers.order.services.SaveOrderService;

public class CheckoutOrderUseCaseImpl implements CheckoutOrderUseCase {

    private final SaveOrderService saveOrderService;
    private final FindOrderService findOrderService;

    public CheckoutOrderUseCaseImpl(SaveOrderService saveOrderService, FindOrderService findOrderService) {
        this.saveOrderService = saveOrderService;
        this.findOrderService = findOrderService;
    }

    @Override
    public void execute(Long orderId) throws BusinessException {
        Order order = findOrderService.findById(orderId).orElseThrow(OrderNotFoundException::new);
        order.setStatus(OrderStatus.PAID);
        saveOrderService.save(order);
    }
    
}
