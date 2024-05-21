package br.com.eighteenburguers.core.ports.outbound.order;

import br.com.eighteenburguers.core.domain.Order;

public interface SaveOrderOutputPort {
    
    Order save(Order order);
}
