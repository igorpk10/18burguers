package br.com.eighteenburguers.core.usecase.order;

import br.com.eighteenburguers.core.domain.Order;
import br.com.eighteenburguers.core.domain.OrderStatus;
import br.com.eighteenburguers.core.exceptions.BusinessException;
import br.com.eighteenburguers.core.exceptions.OrderNotFoundException;
import br.com.eighteenburguers.core.ports.inbound.order.CheckoutOrderInputPort;
import br.com.eighteenburguers.core.ports.outbound.order.FindOrderOutputPort;
import br.com.eighteenburguers.core.ports.outbound.order.SaveOrderOutputPort;

public class CheckoutOrderUseCase implements CheckoutOrderInputPort {

    private final SaveOrderOutputPort saveOrderOutputPort;
    private final FindOrderOutputPort findOrderOutputPort;

    public CheckoutOrderUseCase(SaveOrderOutputPort saveOrderOutputPort, FindOrderOutputPort findOrderOutputPort) {
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
