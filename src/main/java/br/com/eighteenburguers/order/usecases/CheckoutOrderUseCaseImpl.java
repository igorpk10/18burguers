package br.com.eighteenburguers.order.usecases;

import br.com.eighteenburguers.order.entitys.Order;
import br.com.eighteenburguers.order.entitys.OrderStatus;
import br.com.eighteenburguers.product.exceptions.BusinessException;
import br.com.eighteenburguers.product.exceptions.OrderNotFoundException;
import br.com.eighteenburguers.order.services.FindOrderService;
import br.com.eighteenburguers.order.services.SaveOrderService;

public class CheckoutOrderUseCaseImpl implements CheckoutOrderUseCase {

    private final SaveOrderService saveOrderOutputPort;
    private final FindOrderService findOrderOutputPort;

    public CheckoutOrderUseCaseImpl(SaveOrderService saveOrderOutputPort, FindOrderService findOrderOutputPort) {
        this.saveOrderOutputPort = saveOrderOutputPort;
        this.findOrderOutputPort = findOrderOutputPort;
    }

    @Override
    public void execute(Long orderId) throws BusinessException {
        Order order = findOrderOutputPort.findById(orderId).orElseThrow(OrderNotFoundException::new);
        order.setStatus(OrderStatus.PAID);
        saveOrderOutputPort.save(order);
    }
    
}
