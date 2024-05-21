package br.com.eighteenburguers.adapters.outbound;

import org.springframework.stereotype.Component;

import br.com.eighteenburguers.core.domain.Order;
import br.com.eighteenburguers.core.ports.outbound.order.SaveOrderOutputPort;

@Component
public class SaveOrderAdapter implements SaveOrderOutputPort {

    @Override
    public Order save(Order order) {
        return order;
    }
    
}
